package com.yape.transaction.service.impl;

import com.yape.transaction.model.entity.Transaction;
import com.yape.transaction.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaConsumerServiceImpl {


    private final TransactionService transactionService;

    @KafkaListener(topics = "transactionStatusUpdated", groupId = "group_id")
    public void consume(Transaction transaction) {
        transactionService.updateTransactionStatus(transaction.getTransactionExternalId(), transaction.getStatus()).subscribe();
    }
}
