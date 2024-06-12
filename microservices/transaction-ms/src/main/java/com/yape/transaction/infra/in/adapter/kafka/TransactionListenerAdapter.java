package com.yape.transaction.infra.in.adapter.kafka;

import com.yape.transaction.application.port.in.UpdateTransactionCommand;
import com.yape.transaction.config.exception.ErrorCode;
import com.yape.transaction.infra.in.adapter.kafka.model.TransactionInModel;
import com.yape.transaction.config.exception.KafkaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TransactionListenerAdapter {
    private final UpdateTransactionCommand updateTransactionCommand;

    public TransactionListenerAdapter(UpdateTransactionCommand updateTransactionCommand) {
        this.updateTransactionCommand = updateTransactionCommand;
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.transaction}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "transactionKafkaListenerContainerFactory"

    )
    public void listen(TransactionInModel message) {
        log.info("TransactionListenerAdapter message received from kafka {}", message);
        try {
            UpdateTransactionCommand.Command command = UpdateTransactionCommand.Command.builder()
                    .code(message.getCode())
                    .id(message.getId())
                    .status(message.getStatus())
                    .build();
            this.updateTransactionCommand.execute(command);
        } catch (Exception ex) {
            log.error("Occurred an error in kafka ", ex);
            throw new KafkaException(ErrorCode.KAFKA_EXCEPTION);
        }
    }
}
