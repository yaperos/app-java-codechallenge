package com.aly.transactions_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aly.transactions_service.model.entities.TransactionStatus;

@Repository
public interface InterfaceTransactionStatusDao extends JpaRepository<TransactionStatus, Long> {

    @Query(value = "select transaction_status_id from transaction_status where code =:code", nativeQuery = true)
    Long listarTransactionStatusId(String code);
}
