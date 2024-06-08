package com.yape.transactiongateway.service;

import com.yape.transactiongateway.model.dto.RegisterTransactionRequest;
import com.yape.transactiongateway.model.dto.TransactionResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {

    Flux<TransactionResponse> getAllTransactions(Integer limit, Integer offset);

    Mono<TransactionResponse> getTransactionByCode(String transactionCode);

    Mono<Void> createTransaction(RegisterTransactionRequest transactionBody);

}
