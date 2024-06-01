package com.yape.transaction.validator.infrastructure.event;

import com.yape.transaction.validator.domain.Transaction;
import com.yape.transaction.validator.domain.service.ValidatorService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CreatedTransactionConsumer {

    private final ValidatorService service;

    public CreatedTransactionConsumer(ValidatorService service) {
        this.service = service;
    }

    @KafkaListener(topics = "createdTransactionTopic", groupId = "validatorConsumerGroup")
    public void consume(Transaction transaction) {
        service.validateTransaction(transaction);
    }

}
