package com.yape.transaction.manager.domain.repository;

import com.yape.transaction.manager.domain.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository {

    List<Transaction> findByExternalId(UUID id);
    Iterable<Transaction> findAll();
    Transaction save(Transaction transaction);

}
