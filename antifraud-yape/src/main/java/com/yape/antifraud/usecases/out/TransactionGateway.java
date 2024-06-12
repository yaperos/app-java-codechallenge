package com.yape.antifraud.usecases.out;

import com.yape.antifraud.entities.models.Transaction;

public interface TransactionGateway {
    void updateStatus(Transaction transaction);
}
