package com.yape.transaction.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yape.transaction.entities.YapeTransaction;

import java.util.UUID;

public interface YapeTransactionRepository extends JpaRepository<YapeTransaction, UUID> {
	YapeTransaction findByTransactionExternalId(UUID transactionExternalId);
}