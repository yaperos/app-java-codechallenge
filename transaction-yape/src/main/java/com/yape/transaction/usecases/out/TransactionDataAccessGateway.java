package com.yape.transaction.usecases.out;

import com.yape.transaction.entities.models.Transaction;

public interface TransactionDataAccessGateway {
    Transaction create(Transaction transaction);
    Transaction update(Transaction transaction);
    Transaction findByTransactionExternalId(Transaction transaction);
}
