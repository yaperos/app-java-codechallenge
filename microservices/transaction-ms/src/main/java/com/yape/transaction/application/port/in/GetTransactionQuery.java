package com.yape.transaction.application.port.in;

import com.yape.transaction.domain.Transaction;

import java.util.List;
import java.util.UUID;

public interface GetTransactionQuery {
    Transaction getTransactionByCode(UUID code);

    List<Transaction> getAllTransactions();
}
