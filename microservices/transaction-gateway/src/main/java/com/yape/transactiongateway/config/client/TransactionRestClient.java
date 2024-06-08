package com.yape.transactiongateway.config.client;

import com.yape.transactiongateway.model.dto.RegisterTransactionRequest;
import com.yape.transactiongateway.model.dto.TransactionResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionRestClient {

    Flux<TransactionResponse> getAllTransactions();

    Mono<TransactionResponse> getTransactionByCode(String transactionCode);

    Mono<Void> registerTransaction(RegisterTransactionRequest request);

}
