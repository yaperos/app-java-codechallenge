package com.yape.antifraud.service;

import com.yape.antifraud.model.entity.Transaction;

public interface KafkaProducerService {
    void sendTransactionStatusEvent(Transaction transaction);
}
