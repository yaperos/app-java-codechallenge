package com.yape.fraudevaluation.kafka.consumer.impl;

import com.yape.fraudevaluation.kafka.consumer.FraudMessageConsumer;
import com.yape.fraudevaluation.model.dto.TransactionDTO;
import com.yape.fraudevaluation.service.FraudEvaluationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FraudMessageConsumerImpl implements FraudMessageConsumer {

    @Value("${fraud-evaluation.kafka-topics.transaction}")
    private String transactionTopic;

    private final FraudEvaluationService fraudEvaluationService;

    @KafkaListener(
        topics = "${fraud-evaluation.kafka-topics.transaction}",
        groupId = "${spring.kafka.consumer.group-id}"
    )
    @Override
    public void retrieveMessage(TransactionDTO object) {
        try {
            log.info("Retreived message from Topic {}. Message {}", transactionTopic, object.toString());

            fraudEvaluationService.evaluateTransaction(object)
                .subscribe();
        } catch (Exception e) {
            log.error("Exception on message send {}", e.toString());
            throw new RuntimeException("Error occurs message retrieve");
        }
    }

}
