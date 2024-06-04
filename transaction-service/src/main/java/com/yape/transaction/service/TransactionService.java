package com.yape.transaction.service;

import com.yape.transaction.model.entity.Transaction;
import com.yape.transaction.model.request.TransactionRequest;
import com.yape.transaction.model.response.TransactionResponse;
import reactor.core.publisher.Mono;

public interface TransactionService {
    Mono<Transaction> createTransaction(TransactionRequest transactionDto);

    Mono<TransactionResponse> getTransaction(String transactionExternalId);

    Mono<Transaction> updateTransactionStatus(String transactionExternalId, String status);
}
