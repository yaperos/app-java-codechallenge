package com.yape.antifraud.repository;

import com.yape.antifraud.model.entity.Transaction;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String> {
}
