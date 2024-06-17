package com.yape.transaction.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.yape.transaction.dtos.YapeTransactionInsertion;
import com.yape.transaction.dtos.YapeTransactionSingleRetrieval;
import com.yape.transaction.entities.TransactionStatus;
import com.yape.transaction.entities.TransactionType;
import com.yape.transaction.entities.YapeTransaction;
import com.yape.transaction.repositories.TransactionStatusRepository;
import com.yape.transaction.repositories.TransactionTypeRepository;
import com.yape.transaction.repositories.YapeTransactionRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
public class YapeTransactionService {
	
	private final ConcurrentHashMap<UUID, CompletableFuture<YapeTransactionSingleRetrieval>> responseFutures = new ConcurrentHashMap<>();

    @Autowired
    private YapeTransactionRepository transactionRepository;    
    
    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    @Autowired
    private TransactionStatusRepository transactionStatusRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    @Autowired
    public YapeTransactionService(YapeTransactionRepository transactionRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.transactionRepository = transactionRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public YapeTransactionSingleRetrieval createTransaction(YapeTransactionInsertion request) {

        Optional<TransactionType> transactionType = transactionTypeRepository.findById(request.getTransferTypeId());
        if (!transactionType.isPresent()) {
            throw new IllegalArgumentException("Invalid transaction type ID: " + request.getTransferTypeId());
        }

        Optional<TransactionStatus> transactionStatus = transactionStatusRepository.findById(1);
        if (!transactionStatus.isPresent()) {
            throw new IllegalArgumentException("Invalid transaction status ID: 1");
        }
        
        YapeTransaction transaction = new YapeTransaction();
        CompletableFuture<YapeTransactionSingleRetrieval> futureResponse = new CompletableFuture<>();
        
        transaction.setAccountExternalIdDebit(request.getAccountExternalIdDebit());
        transaction.setAccountExternalIdCredit(request.getAccountExternalIdCredit());
        transaction.setTransactionType(transactionType.get());
        transaction.setValue(request.getValue());
        transaction.setTransactionStatus(transactionStatus.get());
        transaction.setCreatedAt(LocalDateTime.now()+"");
        transactionRepository.save(transaction);
        responseFutures.put(transaction.getTransactionExternalId(), futureResponse);

        kafkaTemplate.send("transaction-events", transaction.getTransactionExternalId()+"", transaction.getValue()+"");
        
        try {
            return futureResponse.get(30, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new RuntimeException("Timeout or interruption while waiting for anti-fraud response", e);
        } finally {
            responseFutures.remove(transaction.getTransactionExternalId());
        }
        
    }
    
    public void handleKafkaResponse(UUID transactionId, YapeTransaction response) {
        CompletableFuture<YapeTransactionSingleRetrieval> future = responseFutures.remove(transactionId);
        if (future != null) {
            future.complete(new YapeTransactionSingleRetrieval(response));
        }
    }

    public YapeTransactionSingleRetrieval getTransaction(UUID transactionExternalId) {
    	Optional<YapeTransaction> transaction = transactionRepository.findById(transactionExternalId);
    	YapeTransactionSingleRetrieval response = new YapeTransactionSingleRetrieval();
    	if(transaction.isPresent()) {
    		response = new YapeTransactionSingleRetrieval(transaction.get());
    	}
        return response;
    }

}
