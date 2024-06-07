package com.yape.fraudevaluation.service;

import com.yape.fraudevaluation.model.dto.TransactionDTO;
import reactor.core.publisher.Mono;

public interface FraudEvaluationService {

    Mono<Void> evaluateTransaction(TransactionDTO transactionDTO);

}
