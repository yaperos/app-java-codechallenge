package com.yape.antifraud.service.impl;

import com.yape.antifraud.model.entity.Transaction;
import com.yape.antifraud.service.AntiFraudService;
import com.yape.antifraud.service.KafkaProducerService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class AntifraudServiceImpl implements AntiFraudService {

    private final KafkaProducerService kafkaProducerService;

    @Override
    public Mono<Transaction> validateTransaction(Transaction transaction) {
        if (transaction.getValue() > 1000) {
            transaction.setStatus("REJECTED");
        } else {
            transaction.setStatus("APPROVED");
        }
        return Mono.just(transaction)
                .doOnSuccess(kafkaProducerService::sendTransactionStatusEvent);
    }
}
