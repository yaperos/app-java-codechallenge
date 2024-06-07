package com.yape.transactionmanagement.service;

import com.yape.transactionmanagement.model.request.RegisterTransactionRequest;
import com.yape.transactionmanagement.model.response.TransactionResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionManagementService {

    Flux<TransactionResponse> getAllTransactions();

    Mono<TransactionResponse> getTransactionByCode(String transactionCode);

    Mono<Void> registerTransaction(RegisterTransactionRequest request);

}
