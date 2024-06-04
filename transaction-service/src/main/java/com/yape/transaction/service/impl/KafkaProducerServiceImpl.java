package com.yape.transaction.service.impl;

import com.yape.transaction.model.entity.Transaction;
import com.yape.transaction.service.KafkaProducerService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaProducerServiceImpl implements KafkaProducerService {

    private final ReactiveKafkaProducerTemplate<String, Transaction> kafkaTemplate;

    @Override
    public void sendTransactionCreatedEvent(Transaction transaction) {
        kafkaTemplate.send("transactionCreated", transaction).subscribe();
    }
}
