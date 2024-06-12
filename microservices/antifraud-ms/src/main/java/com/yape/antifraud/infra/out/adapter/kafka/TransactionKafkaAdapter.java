package com.yape.antifraud.infra.out.adapter.kafka;

import com.yape.antifraud.application.port.out.TransactionEdaRepository;
import com.yape.antifraud.config.exception.ErrorCode;
import com.yape.antifraud.config.properties.SpringConfigurationProperties;
import com.yape.antifraud.config.exception.KafkaException;
import com.yape.antifraud.infra.out.adapter.kafka.model.TransactionOutModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TransactionKafkaAdapter implements TransactionEdaRepository {
    private final KafkaTemplate<String, TransactionOutModel> kafkaTemplate;
    private final SpringConfigurationProperties springConfigurationProperties;
    public TransactionKafkaAdapter(KafkaTemplate<String, TransactionOutModel> kafkaTemplate, SpringConfigurationProperties springConfigurationProperties) {
        this.kafkaTemplate = kafkaTemplate;
        this.springConfigurationProperties = springConfigurationProperties;
    }
    @Override
    public void updateTransaction(TransactionOutModel transactionModel) {
        try {
            log.info("sending to Transaction for update, transaction id:{},  status:{}", transactionModel.getId(), transactionModel.getStatus() );
            this.kafkaTemplate.send(springConfigurationProperties.getKafka().getTopic().getTransaction(), transactionModel);
            this.kafkaTemplate.flush();
        } catch (Exception e) {
            log.error("Occurred an error in kafka ", e);
            throw new KafkaException(ErrorCode.KAFKA_EXCEPTION);
        }
    }
}
