package com.yape.transaction.manager.domain.service;

import com.yape.transaction.manager.domain.Transaction;
import com.yape.transaction.manager.domain.event.CreateTransactionEventPort;
import com.yape.transaction.manager.domain.repository.TransactionRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class DomainTransactionService implements TransactionService {

    private final TransactionRepository repository;
    private final CreateTransactionEventPort port;

    public DomainTransactionService(TransactionRepository repository, CreateTransactionEventPort port) {
        this.repository = repository;
        this.port = port;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {

        Transaction registeredTransaction = repository.save(transaction);

        port.sendCreateTransactionEvent(registeredTransaction);

        return registeredTransaction;
    }

    @Override
    public List<Transaction> findTransactionsByExternalId(String externalId) {
        return repository.findByExternalId(UUID.fromString(externalId));
    }

}
