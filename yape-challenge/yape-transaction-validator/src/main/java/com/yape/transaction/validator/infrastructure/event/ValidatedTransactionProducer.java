package com.yape.transaction.validator.infrastructure.event;

import com.yape.transaction.validator.domain.Transaction;
import com.yape.transaction.validator.domain.event.ValidatedTransactionEventPort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class ValidatedTransactionProducer implements ValidatedTransactionEventPort {

    private KafkaTemplate<String, String> kafkaTemplate;

    public ValidatedTransactionProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendValidatedTransactionEvent(Transaction transaction) {

        Message<Transaction> transactionMessage = MessageBuilder
                .withPayload(transaction)
                .setHeader(KafkaHeaders.TOPIC, "validatedTransactionTopic")
                .build();

        kafkaTemplate.send(transactionMessage);
    }
}
