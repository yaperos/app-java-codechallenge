package com.yape.codechallenge.service.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.yape.codechallenge.model.Transaction;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProducerService {

    private static final String TOPIC = "transaction_created";

    private final KafkaTemplate<String, Transaction> kafkaTemplate;

    public ProducerService(KafkaTemplate<String, Transaction> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTransaction(Transaction transaction) {
        kafkaTemplate.send(TOPIC, transaction);
        log.info("Publishing transaction to Kafka: {}", transaction);
    }
}
