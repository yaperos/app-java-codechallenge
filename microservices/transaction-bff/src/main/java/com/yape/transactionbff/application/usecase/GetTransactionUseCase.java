package com.yape.transactionbff.application.usecase;

import com.yape.transactionbff.application.domain.Transaction;
import com.yape.transactionbff.application.port.in.GetTransactionQuery;
import com.yape.transactionbff.application.port.out.TransactionClient;
import com.yape.transactionbff.infra.in.adapter.graphql.model.TransactionModelResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GetTransactionUseCase implements GetTransactionQuery {
    private final TransactionClient transactionClient;

    public GetTransactionUseCase(TransactionClient transactionClient){
        this.transactionClient = transactionClient;
    }
    @Override
    public Transaction getTransactionByCode(UUID code) {
        return this.transactionClient.getTransactionById(code).toDomain();
    }
    @Override
    public List<Transaction> getAllTransactions() {
        return this.transactionClient.getAllTransactions().stream()
                .map(TransactionModelResponse::toDomain)
                .collect(Collectors.toList());
    }

}
