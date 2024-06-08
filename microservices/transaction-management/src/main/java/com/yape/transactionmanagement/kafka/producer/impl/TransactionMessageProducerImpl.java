package com.yape.transactionmanagement.kafka.producer.impl;

import com.yape.transactionmanagement.kafka.producer.TransactionMessageProducer;
import com.yape.transactionmanagement.model.dto.TransactionDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionMessageProducerImpl implements TransactionMessageProducer {

    private final KafkaTemplate<String, TransactionDTO> kafkaTemplate;

    @Value("${transaction-management.kafka-topics.transaction}")
    private String transactionTopic;

    @Override
    public boolean sendMessage(TransactionDTO transactionDTO) {
        try {
            log.info("Sending message {} to Topic {}", transactionDTO.toString(), transactionTopic);

            kafkaTemplate.send(transactionTopic, transactionDTO);

            return true;
        } catch (Exception e) {
            log.error("Exception on message send {}", e.toString());
            throw new RuntimeException("Error occurs on message send");
        }
    }

}
