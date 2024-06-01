package com.yape.transaction.manager.infrastructure.event;

import com.yape.transaction.manager.domain.Transaction;
import com.yape.transaction.manager.domain.repository.TransactionRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ValidatedTransactionConsumer {

    private final TransactionRepository repository;

    public ValidatedTransactionConsumer(TransactionRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "validatedTransactionTopic", groupId = "managerConsumerGroup")
    public void consume(Transaction event) {
        repository.save(event);
    }
}