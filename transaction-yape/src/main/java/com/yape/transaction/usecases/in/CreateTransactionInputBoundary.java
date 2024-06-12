package com.yape.transaction.usecases.in;

import com.yape.transaction.usecases.models.CreateTransactionModel;
import com.yape.transaction.entities.models.Transaction;

public interface CreateTransactionInputBoundary {
        Transaction createTransaction(CreateTransactionModel createTransactionModel);
}
