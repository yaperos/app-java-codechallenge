package com.yape.antifraud.infra.in.adapter.kafka;

import com.yape.antifraud.application.port.in.ReviewAntiFraudCommand;
import com.yape.antifraud.config.exception.ErrorCode;
import com.yape.antifraud.infra.in.adapter.kafka.model.TransactionInModel;
import com.yape.antifraud.config.exception.KafkaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AntiFraudListenerAdapter {
    private final ReviewAntiFraudCommand reviewAntiFraudCommand;

    public AntiFraudListenerAdapter(ReviewAntiFraudCommand reviewAntiFraudCommand) {
        this.reviewAntiFraudCommand = reviewAntiFraudCommand;
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.antifraud}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "antifraudKafkaListenerContainerFactory"

    )
    public void listen(TransactionInModel message) {
        log.info("AntiFraudListenerAdapter message received from kafka {}", message);
        try {
            ReviewAntiFraudCommand.Command command = ReviewAntiFraudCommand.Command.builder()
                    .id(message.getId())
                    .code(message.getCode())
                    .status(message.getStatus())
                    .value(message.getValue())
                    .build();
            this.reviewAntiFraudCommand.execute(command);
        } catch (Exception ex) {
            log.error("Occurred an error in kafka ", ex);
            throw new KafkaException(ErrorCode.KAFKA_EXCEPTION);
        }
    }
}
