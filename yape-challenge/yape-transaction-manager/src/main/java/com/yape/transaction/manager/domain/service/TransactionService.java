package com.yape.transaction.manager.domain.service;

import com.yape.transaction.manager.domain.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {

    Transaction createTransaction (Transaction transaction);
    List<Transaction> findTransactionsByExternalId (String externalId);
}
