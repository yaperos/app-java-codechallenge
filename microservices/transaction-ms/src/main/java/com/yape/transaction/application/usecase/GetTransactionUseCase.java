package com.yape.transaction.application.usecase;

import com.yape.transaction.application.exception.NotFoundException;
import com.yape.transaction.application.port.in.GetTransactionQuery;
import com.yape.transaction.application.port.out.TransactionRepository;
import com.yape.transaction.domain.Transaction;
import com.yape.transaction.infra.out.adapter.data.model.TransactionDataModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GetTransactionUseCase implements GetTransactionQuery {
    private final TransactionRepository repository;

    public GetTransactionUseCase(TransactionRepository repository){
        this.repository = repository;
    }
    @Override
    public Transaction getTransactionByCode(UUID code) {
        return this.repository.findOptionalByCode(code)
                .orElseThrow(() -> new NotFoundException("Transaction not found"))
                .toDomain();
    }
    @Override
    public List<Transaction> getAllTransactions() {
        return this.repository.findAll().stream()
                .map(TransactionDataModel::toDomain)
                .collect(Collectors.toList());
    }

}
