package com.yape.transactionbff.application.port.in;

import com.yape.transactionbff.application.domain.Transaction;

import java.util.List;
import java.util.UUID;

public interface GetTransactionQuery {
    Transaction getTransactionByCode(UUID code);
    List<Transaction> getAllTransactions();
}
