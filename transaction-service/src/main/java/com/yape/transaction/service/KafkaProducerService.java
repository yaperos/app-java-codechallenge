package com.yape.transaction.service;

import com.yape.transaction.model.entity.Transaction;

public interface KafkaProducerService {
    void sendTransactionCreatedEvent(Transaction transaction);
}
