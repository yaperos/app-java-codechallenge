package com.yape.transaction.infra.out.adapter.cache;

import com.yape.transaction.application.exception.NotFoundException;
import com.yape.transaction.application.port.out.CacheRepository;
import com.yape.transaction.application.port.out.TransactionRepository;
import com.yape.transaction.domain.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CacheRepositoryAdapter implements CacheRepository {
    private final TransactionRepository repository;
    public CacheRepositoryAdapter(TransactionRepository repository){
        this.repository = repository;
    }
    @Cacheable(cacheNames= "transactionCache", key="#id")
    @Override
    public Transaction findTransactionById(Long id){
            return this.repository.findOptionalById(id)
                    .orElseThrow(() -> new NotFoundException("transaction not found")).toDomain();
        }

    @CacheEvict(cacheNames = "transactionCache", key = "#id")
    @Override
    public void removeTransactionById(Long id) {
        log.info("deleting from cache id:{}", id);
    }

    @CachePut(cacheNames="transactionCache", key="#transaction.id")
    @Override
    public Transaction saveTransaction(Transaction transaction) {
        log.info("saving cache id:{}", transaction.getId());
        return transaction;
    }
}
