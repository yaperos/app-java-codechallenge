package com.yape.transaction.usecases.in;

import com.yape.transaction.usecases.models.UpdateTransactionModel;
import com.yape.transaction.entities.models.Transaction;

public interface UpdateTransactionInputBoundary {
        Transaction updateTransaction(UpdateTransactionModel updateTransactionModel);
}
