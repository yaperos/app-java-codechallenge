package com.aly.transactions_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aly.transactions_service.model.entities.TransactionExternal;

@Repository
public interface InterfaceTransactionExternalDao extends JpaRepository<TransactionExternal, Long> {

}
