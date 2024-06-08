package com.yape.codechallenge.service.kafka;

import java.math.BigDecimal;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.yape.codechallenge.model.Transaction;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class AntiFraudService {

    private static final String VALIDATION_TOPIC = "transaction_validated";


    private final KafkaTemplate<String, Transaction> kafkaTemplate;

    public AntiFraudService(KafkaTemplate<String, Transaction> kafkaTemplate
            ) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "transaction_created", groupId = "transaction-group")
    public void validateTransaction(Transaction transaction) {
        if (transaction.getValue().compareTo(BigDecimal.valueOf(1000)) > 0) {
            transaction.setTransactionStatus("rejected");
        } else {
            transaction.setTransactionStatus("approved");
        }
        kafkaTemplate.send(VALIDATION_TOPIC, transaction);
        log.info("Transaction validated {} And Topic {}", transaction, VALIDATION_TOPIC);
    }

}
