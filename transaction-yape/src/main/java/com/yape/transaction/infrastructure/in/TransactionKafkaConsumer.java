package com.yape.transaction.infrastructure.in;

import com.yape.transaction.usecases.in.UpdateTransactionInputBoundary;
import com.yape.transaction.infrastructure.models.TransactionMessageModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TransactionKafkaConsumer {
    private final UpdateTransactionInputBoundary updateTransactionInputBoundary;

    public TransactionKafkaConsumer(UpdateTransactionInputBoundary updateTransactionInputBoundary) {
        this.updateTransactionInputBoundary = updateTransactionInputBoundary;
    }

    @KafkaListener(topics = "${spring.kafka.topic.transaction-update}", groupId = "${spring.kafka.consumer.group-id}")
    public void updateTransaction(TransactionMessageModel transactionMessageModel) {
        log.info("TransactionKafkaConsumer updateTransaction transactionMessageModel {}", transactionMessageModel);
        updateTransactionInputBoundary.updateTransaction(transactionMessageModel.getUseCaseModel());
    }
}
