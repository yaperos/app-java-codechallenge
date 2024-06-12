package com.yape.transaction.infrastructure.out;

import com.yape.transaction.usecases.out.TransactionDataAccessGateway;
import com.yape.transaction.entities.models.Transaction;
import com.yape.transaction.infrastructure.models.persistance.TransactionRepository;
import com.yape.transaction.infrastructure.models.persistance.TransactionSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@CacheConfig(cacheNames = "transactionCache")
public class TransactionJpaGateway implements TransactionDataAccessGateway {

    public TransactionRepository transactionRepository;

    public TransactionJpaGateway(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    @CachePut(value = "transactions", key = "#transaction.transactionExternalId")
    public Transaction create(Transaction transaction) {
        log.info("TransactionJpaGateway create transaction {}", transaction);
        return transactionRepository.save(TransactionSchema.convertFrom(transaction)).getEntity();
    }

    @Override
    @CachePut(value = "transactions", key = "#transaction.transactionExternalId")
    public Transaction update(Transaction transaction) {
        log.info("TransactionJpaGateway update transaction {}", transaction);
        return transactionRepository.save(TransactionSchema.convertFrom(transaction)).getEntity();
    }

    @Override
    @Cacheable(value = "transactions", key = "#transaction.transactionExternalId", unless="#result == null")
    public Transaction findByTransactionExternalId(Transaction transaction) {
        log.info("TransactionJpaGateway findByTransactionExternalId transaction {}", transaction);
        TransactionSchema transactionSchema = transactionRepository.findByTransactionExternalId(transaction.getTransactionExternalId());
        if (transactionSchema != null) {
            return transactionSchema.getEntity();
        }
        return null;
    }
}
