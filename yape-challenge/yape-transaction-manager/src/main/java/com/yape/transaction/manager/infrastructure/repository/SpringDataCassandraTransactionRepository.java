package com.yape.transaction.manager.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.yape.transaction.manager.domain.Transaction;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataCassandraTransactionRepository extends CassandraRepository<TransactionEntity, UUID> {

    List<TransactionEntity> findByExternalId(UUID externalId);
}