package com.yape.transaction.infra.out.adapter.kafka;

import com.yape.transaction.application.port.out.AntiFraudEdaRepository;
import com.yape.transaction.config.exception.ErrorCode;
import com.yape.transaction.config.properties.SpringConfigurationProperties;
import com.yape.transaction.domain.Transaction;
import com.yape.transaction.config.exception.KafkaException;
import com.yape.transaction.infra.out.adapter.kafka.model.TransactionOutModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AntiFraudKafkaAdapter implements AntiFraudEdaRepository {
    private final KafkaTemplate<String, TransactionOutModel> kafkaTemplate;
    private final SpringConfigurationProperties springConfigurationProperties;

    public AntiFraudKafkaAdapter(KafkaTemplate<String, TransactionOutModel> kafkaTemplate, SpringConfigurationProperties springConfigurationProperties) {
        this.kafkaTemplate = kafkaTemplate;
        this.springConfigurationProperties = springConfigurationProperties;
    }

    @Override
    public void validate(Transaction transaction) {
        TransactionOutModel transactionModel = TransactionOutModel.fromDomain(transaction);
        try {
            log.info("sending to Anfifraud, transaction id:{}", transactionModel.getId() );
            this.kafkaTemplate.send(springConfigurationProperties.getKafka().getTopic().getAntifraud(), transactionModel);
            this.kafkaTemplate.flush();
        } catch (Exception e) {
            log.error("Occurred an error in kafka ", e);
            throw new KafkaException(ErrorCode.KAFKA_EXCEPTION);
        }

    }
}
