package com.yape.antifraud.listeners;


import com.yape.antifraud.services.AntiFraudService;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AntifraudListenerTransactions {

    @Autowired
    private AntiFraudService antiFraudService;

    @KafkaListener(topics = "transaction-events", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(ConsumerRecord<String, String> record) {
        antiFraudService.checkTransaction(record.key(), record.value());
    }
}