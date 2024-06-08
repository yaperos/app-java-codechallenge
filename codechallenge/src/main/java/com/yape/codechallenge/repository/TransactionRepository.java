package com.yape.codechallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yape.codechallenge.model.Transaction;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID>{
    
}
