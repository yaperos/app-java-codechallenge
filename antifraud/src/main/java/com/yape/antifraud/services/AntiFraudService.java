package com.yape.antifraud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AntiFraudService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void checkTransaction(String transactionId, String value) {
        double transactionValue = Double.parseDouble(value);
        String status = transactionValue > 1000 || transactionValue <=0 ? "3" : "2";
        kafkaTemplate.send("validated-transactions", transactionId, status);
    }
}
