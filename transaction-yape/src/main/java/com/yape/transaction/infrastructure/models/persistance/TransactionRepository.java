package com.yape.transaction.infrastructure.models.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<TransactionSchema, Long> {

    TransactionSchema findByTransactionExternalId(UUID transactionExternalId);

}
