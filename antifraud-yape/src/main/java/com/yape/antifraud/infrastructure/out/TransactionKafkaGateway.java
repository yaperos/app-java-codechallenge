package com.yape.antifraud.infrastructure.out;

import com.yape.antifraud.usecases.out.TransactionGateway;
import com.yape.antifraud.entities.models.Transaction;
import com.yape.antifraud.infrastructure.models.TransactionMessageModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TransactionKafkaGateway implements TransactionGateway {

    @Value("${spring.kafka.topic.transaction-update}")
    private String kafkaTopicTransaction;

    private final KafkaTemplate<String, TransactionMessageModel> kafkaTemplate;

    public TransactionKafkaGateway(KafkaTemplate<String, TransactionMessageModel> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void updateStatus(Transaction transaction) {
        log.info("TransactionKafkaGateway updateStatus transaction {}", transaction);
        kafkaTemplate.send(kafkaTopicTransaction, TransactionMessageModel.getInstanceFrom(transaction));
    }
}
