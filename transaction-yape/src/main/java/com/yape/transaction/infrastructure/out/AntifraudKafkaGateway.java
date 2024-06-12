package com.yape.transaction.infrastructure.out;

import com.yape.transaction.usecases.out.AntifraudGateway;
import com.yape.transaction.entities.models.Transaction;
import com.yape.transaction.infrastructure.models.AntifraudMessageModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AntifraudKafkaGateway implements AntifraudGateway {

    @Value("${spring.kafka.topic.antifraud-validate}")
    private String kafkaTopicAntifraud;

    private final KafkaTemplate<String, AntifraudMessageModel> kafkaTemplate;

    public AntifraudKafkaGateway(KafkaTemplate<String, AntifraudMessageModel> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void validate(Transaction transaction) {
        log.info("AntifraudKafkaGateway validate transaction {}", transaction);
        kafkaTemplate.send(kafkaTopicAntifraud, AntifraudMessageModel.getInstanceFrom(transaction));
    }
}