package com.yape.transaction.usecases.out;

import com.yape.transaction.entities.models.Transaction;

public interface AntifraudGateway {
    void validate(Transaction transaction);
}
