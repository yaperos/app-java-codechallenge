package com.yape.transaction.service;

import com.yape.transaction.model.entity.Transaction;

public interface KafkaConsumerService {

    void consume(Transaction transaction);
}
