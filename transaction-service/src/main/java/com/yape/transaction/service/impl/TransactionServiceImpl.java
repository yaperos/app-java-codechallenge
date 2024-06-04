package com.yape.transaction.service.impl;

import com.yape.transaction.builder.TransactionBuilder;
import com.yape.transaction.model.entity.Transaction;
import com.yape.transaction.model.request.TransactionRequest;
import com.yape.transaction.model.response.TransactionResponse;
import com.yape.transaction.repository.TransactionRepository;
import com.yape.transaction.service.KafkaProducerService;
import com.yape.transaction.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.yape.transaction.builder.TransactionBuilder.buildTransactionFromRequest;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {


    private final TransactionRepository transactionRepository;


    private final KafkaProducerService kafkaProducer;

    @Override
    public Mono<Transaction> createTransaction(TransactionRequest transactionRequest) {

        Transaction transaction = new Transaction();
        transaction.setTransactionExternalId(UUID.randomUUID().toString());
        transaction.setAccountExternalIdDebit(transactionRequest.getAccountExternalIdDebit());
        transaction.setAccountExternalIdCredit(transactionRequest.getAccountExternalIdCredit());
        transaction.setTransferTypeId(transactionRequest.getTransferTypeId());
        transaction.setValue(transactionRequest.getValue());
        transaction.setStatus("PENDING");
        transaction.setCreatedAt(LocalDateTime.now());

        return transactionRepository.save(buildTransactionFromRequest(transactionRequest))
                .doOnSuccess(kafkaProducer::sendTransactionCreatedEvent);
    }

    @Override
    public Mono<TransactionResponse> getTransaction(String transactionExternalId) {
        return transactionRepository.findById(transactionExternalId)
        .map(TransactionBuilder::buildTransactionToResponse);
    }

    @Override
    public Mono<Transaction> updateTransactionStatus(String transactionExternalId, String status) {
        return transactionRepository.findById(transactionExternalId)
                .flatMap(transaction -> {
                    transaction.setStatus(status);
                    return transactionRepository.save(transaction);
                });
    }
}
