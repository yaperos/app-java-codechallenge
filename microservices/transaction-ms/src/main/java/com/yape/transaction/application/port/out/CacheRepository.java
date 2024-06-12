package com.yape.transaction.application.port.out;

import com.yape.transaction.domain.Transaction;


public interface CacheRepository {
    Transaction findTransactionById(Long id);
    void removeTransactionById(Long id);
    Transaction saveTransaction(Transaction transaction);
}


