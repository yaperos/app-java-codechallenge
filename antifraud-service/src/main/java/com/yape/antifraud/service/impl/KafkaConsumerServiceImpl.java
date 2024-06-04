package com.yape.antifraud.service.impl;

import com.yape.antifraud.model.entity.Transaction;
import com.yape.antifraud.service.AntiFraudService;
import com.yape.antifraud.service.KafkaConsumerService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    private final AntiFraudService antiFraudService;

    @Override
    @KafkaListener(topics = "transactionCreated", groupId = "group_id")
    public void consume(@Payload Transaction transaction) {
        System.out.println("inicio de consumo");
        antiFraudService.validateTransaction(transaction).subscribe();
        System.out.println("consumio validacion");
    }
}
