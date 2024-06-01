package com.yape.transaction.manager.infrastructure.event;

import com.yape.transaction.manager.domain.Transaction;
import com.yape.transaction.manager.domain.event.CreateTransactionEventPort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class CreatedTransactionProducer implements CreateTransactionEventPort {

    private KafkaTemplate<String, Transaction> kafkaTemplate;

    public CreatedTransactionProducer(KafkaTemplate<String, Transaction> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendCreateTransactionEvent(Transaction transaction) {

        Message<Transaction> transactionMessage = MessageBuilder
                .withPayload(transaction)
                .setHeader(KafkaHeaders.TOPIC, "createdTransactionTopic")
                .build();

        kafkaTemplate.send(transactionMessage);

    }
}
