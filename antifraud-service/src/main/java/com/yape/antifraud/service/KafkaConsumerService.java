package com.yape.antifraud.service;

import com.yape.antifraud.model.entity.Transaction;

public interface KafkaConsumerService {
    void consume(Transaction transaction);
}
