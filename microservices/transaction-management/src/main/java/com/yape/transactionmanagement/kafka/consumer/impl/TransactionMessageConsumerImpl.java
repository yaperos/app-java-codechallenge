package com.yape.transactionmanagement.kafka.consumer.impl;

import com.yape.transactionmanagement.kafka.consumer.TransactionMessageConsumer;
import com.yape.transactionmanagement.model.dto.TransactionDTO;
import com.yape.transactionmanagement.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionMessageConsumerImpl implements TransactionMessageConsumer {

    @Value("${transaction-management.kafka-topics.fraud-evaluation}")
    private String fraudTopic;

    private final TransactionRepository transactionRepository;

    @Caching(evict = {
        @CacheEvict(value = "transactions", allEntries = true),
        @CacheEvict(value = "transaction", key = "#object.transactionId")
    })
    @KafkaListener(
        topics = "${transaction-management.kafka-topics.fraud-evaluation}",
        groupId = "${spring.kafka.consumer.group-id}"
    )
    @Override
    public void retrieveMessage(TransactionDTO object) {
        try {
            log.info("Retreived message from Topic {}. Message {}", fraudTopic, object.toString());

            transactionRepository.updateTransactionState(object.getTransactionId(), object.getTransactionState());
        } catch (Exception e) {
            log.error("Exception on message send {}", e.toString());
            throw new RuntimeException("Error occurs message retrieve");
        }
    }

}
