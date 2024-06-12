package com.yape.transaction.usecases.in;

import com.yape.transaction.usecases.models.GetTransactionModel;
import com.yape.transaction.entities.models.Transaction;

public interface GetTransactionInputBoundary {
        Transaction getTransaction(GetTransactionModel getTransactionModel);
}
