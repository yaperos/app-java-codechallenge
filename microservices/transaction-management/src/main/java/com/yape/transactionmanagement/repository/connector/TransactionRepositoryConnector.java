package com.yape.transactionmanagement.repository.connector;

import com.yape.transactionmanagement.model.entity.TransactionEntity;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TransactionRepositoryConnector {

    Mono<List<TransactionEntity>> findAllTransactions();

    Mono<TransactionEntity> findTransactionByCode(String transactionCode);

    Mono<TransactionEntity> saveTransaction(TransactionEntity entity);

}
