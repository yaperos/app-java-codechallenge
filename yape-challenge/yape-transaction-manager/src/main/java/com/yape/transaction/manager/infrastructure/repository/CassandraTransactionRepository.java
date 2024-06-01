package com.yape.transaction.manager.infrastructure.repository;

import com.yape.transaction.manager.domain.Transaction;
import com.yape.transaction.manager.domain.repository.TransactionRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CassandraTransactionRepository implements TransactionRepository {

    private final SpringDataCassandraTransactionRepository repository;

    public CassandraTransactionRepository(SpringDataCassandraTransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Transaction> findByExternalId(UUID id) {
        return repository.findByExternalId(id)
                .stream()
                .map(TransactionEntity::toDomainTransaction)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Transaction> findAll() {

        List<TransactionEntity> transactions = repository.findAll();

        return transactions.stream().map(TransactionEntity::toDomainTransaction).collect(Collectors.toList());
    }

    @Override
    public Transaction save(Transaction transaction) {
        return repository.save(new TransactionEntity(transaction)).toDomainTransaction();
    }
}
