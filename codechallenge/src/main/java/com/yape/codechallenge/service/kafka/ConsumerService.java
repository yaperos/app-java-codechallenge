package com.yape.codechallenge.service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.yape.codechallenge.model.Transaction;
import com.yape.codechallenge.repository.TransactionRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ConsumerService {

    private final TransactionRepository transactionRepository;

    public ConsumerService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @KafkaListener(topics = "transaction_validated", groupId = "transaction-group")
    public void updateTransactionStatus(Transaction transaction) {
        Transaction existingTransaction = transactionRepository.findById(transaction.getId())
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        existingTransaction.setTransactionStatus(transaction.getTransactionStatus());
        transactionRepository.save(existingTransaction);
        log.info("Transaction status updated: {}", existingTransaction);
    }
}
