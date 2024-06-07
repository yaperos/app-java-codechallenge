package com.yape.fraudevaluation.service.impl;

import com.yape.fraudevaluation.kafka.producer.FraudMessageProducer;
import com.yape.fraudevaluation.model.constant.TransactionStatusEnum;
import com.yape.fraudevaluation.model.dto.TransactionDTO;
import com.yape.fraudevaluation.service.FraudEvaluationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class FraudEvaluationServiceImpl implements FraudEvaluationService {

    @Value("${fraud-evaluation.value-limit}")
    private BigDecimal valueLimit;

    private final FraudMessageProducer fraudMessageProducer;

    @Override
    public Mono<Void> evaluateTransaction(TransactionDTO transactionDTO) {
        return Mono.just(transactionDTO)
            .filter(transaction -> TransactionStatusEnum.PENDING.getDescription().equals(transactionDTO.getTransactionState()))
            .doOnNext(this::evaluateValue)
            .flatMap(transaction -> Mono.fromCallable(() -> fraudMessageProducer.sendMessage(transaction))
                .then());
    }

    private void evaluateValue(TransactionDTO transaction) {
        transaction.setTransactionState( (transaction.getTransactionValue().compareTo(valueLimit) > 0)
            ? TransactionStatusEnum.REJECTED.getDescription()
            : TransactionStatusEnum.APPROBED.getDescription());
    }

}
