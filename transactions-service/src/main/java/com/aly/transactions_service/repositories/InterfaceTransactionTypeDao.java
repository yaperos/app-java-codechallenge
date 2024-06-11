package com.aly.transactions_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aly.transactions_service.model.entities.TransactionType;

@Repository
public interface InterfaceTransactionTypeDao extends JpaRepository<TransactionType, Long> {

    @Query(value = "select transaction_type_id from transaction_type where code =:code", nativeQuery = true)
    Long listarTransactionTypeId(String code);
}
