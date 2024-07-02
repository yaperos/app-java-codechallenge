package com.pe.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pe.transaction.model.entity.TransactionEntity;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity,Long> {

    TransactionEntity findByTransactionExternalId(String transactionExternalId);

}