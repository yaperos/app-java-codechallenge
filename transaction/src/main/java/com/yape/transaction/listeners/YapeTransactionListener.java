package com.yape.transaction.listeners;

import com.yape.transaction.entities.TransactionStatus;
import com.yape.transaction.entities.YapeTransaction;
import com.yape.transaction.repositories.TransactionStatusRepository;
import com.yape.transaction.repositories.YapeTransactionRepository;
import com.yape.transaction.services.YapeTransactionService;

import java.util.Optional;
import java.util.UUID;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class YapeTransactionListener {
	
	private final YapeTransactionService transactionService;

	@Autowired
	private YapeTransactionRepository transactionRepository;
	
	@Autowired
	private TransactionStatusRepository statusRepository;
	
	@Autowired
    public YapeTransactionListener(YapeTransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @KafkaListener(topics = "validated-transactions", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(ConsumerRecord<String, String> record) {
        String transactionExternalId = record.key();
        int transactionStatus = Integer.parseInt(record.value());
        
        Optional<YapeTransaction> transaction = transactionRepository.findById(UUID.fromString(transactionExternalId));
        Optional<TransactionStatus> status = statusRepository.findById(transactionStatus);
        
        transaction.ifPresent(existingTransaction ->{
        	status.ifPresent(existingStatus->{        		
        		existingTransaction.setTransactionStatus(existingStatus);
	            transactionRepository.save(existingTransaction);
	            transactionService.handleKafkaResponse(existingTransaction.getTransactionExternalId(), existingTransaction);
	        		
        	});        	
        });
    }
}