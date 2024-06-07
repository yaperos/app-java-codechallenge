package com.yape.fraudevaluation.kafka.producer.impl;

import com.yape.fraudevaluation.kafka.producer.FraudMessageProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.yape.fraudevaluation.model.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FraudMessageProducerImpl implements FraudMessageProducer {

    private final KafkaTemplate<String, TransactionDTO> kafkaTemplate;

    @Value("${fraud-evaluation.kafka-topics.fraud-evaluation}")
    private String fraudTopic;

    @Override
    public boolean sendMessage(TransactionDTO transactionDTO) {
        try {
            log.info("Sending message {} to Topic {}", transactionDTO.toString(), fraudTopic);

            kafkaTemplate.send(fraudTopic, transactionDTO);

            return true;
        } catch (Exception e) {
            log.error("Exception on message send {}", e.toString());
            throw new RuntimeException("Error occurs on message send");
        }
    }

}
