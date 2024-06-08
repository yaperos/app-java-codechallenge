package com.yape.transactiongateway.service.impl;

import com.yape.transactiongateway.config.client.TransactionRestClient;
import com.yape.transactiongateway.model.dto.RegisterTransactionRequest;
import com.yape.transactiongateway.model.dto.TransactionResponse;
import com.yape.transactiongateway.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRestClient transactionRestClient;

    @Override
    public Flux<TransactionResponse> getAllTransactions(Integer limit, Integer offset) {
        Flux<TransactionResponse> allTransactions = transactionRestClient.getAllTransactions();

        if (Objects.nonNull(offset)) allTransactions = allTransactions.skip(offset);

        if (Objects.nonNull(limit)) allTransactions = allTransactions.take(limit);

        return allTransactions;
    }

    @Override
    public Mono<TransactionResponse> getTransactionByCode(String transactionCode) {
        return transactionRestClient.getTransactionByCode(transactionCode);
    }

    @Override
    public Mono<Void> createTransaction(RegisterTransactionRequest transactionBody) {
        return transactionRestClient.registerTransaction(transactionBody);
    }

}
