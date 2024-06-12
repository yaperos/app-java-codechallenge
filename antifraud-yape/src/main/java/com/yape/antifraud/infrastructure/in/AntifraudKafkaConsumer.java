package com.yape.antifraud.infrastructure.in;

import com.yape.antifraud.infrastructure.models.AntifraudMessageModel;
import com.yape.antifraud.usecases.in.AntifraudValidateInputBoundary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AntifraudKafkaConsumer {

    private final AntifraudValidateInputBoundary antifraudValidateInputBoundary;

    public AntifraudKafkaConsumer(AntifraudValidateInputBoundary antifraudValidateInputBoundary) {
        this.antifraudValidateInputBoundary = antifraudValidateInputBoundary;
    }

    @KafkaListener(topics = "${spring.kafka.topic.antifraud-validate}", groupId = "${spring.kafka.consumer.group-id}")
    public void antifraudValidate(AntifraudMessageModel antifraudMessageModel) {
        log.info("AntifraudKafkaConsumer antifraudValidate antifraudMessageModel {}", antifraudMessageModel);
        antifraudValidateInputBoundary.antifraudValidate(antifraudMessageModel.getUseCaseModel());
    }
}