package com.yape.antifraud.service;

import com.yape.antifraud.model.entity.Transaction;
import reactor.core.publisher.Mono;

public interface AntiFraudService {
    Mono<Transaction> validateTransaction(Transaction transaction);
}
