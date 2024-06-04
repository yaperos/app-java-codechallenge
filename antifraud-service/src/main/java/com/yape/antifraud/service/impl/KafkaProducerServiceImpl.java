package com.yape.antifraud.service.impl;

import com.yape.antifraud.model.entity.Transaction;
import com.yape.antifraud.service.KafkaProducerService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaProducerServiceImpl implements KafkaProducerService {

    private ReactiveKafkaProducerTemplate<String, Transaction> kafkaTemplate;

    @Override
    public void sendTransactionStatusEvent(Transaction transaction) {
        kafkaTemplate.send("transactionStatusUpdated", transaction).subscribe();
    }
}
