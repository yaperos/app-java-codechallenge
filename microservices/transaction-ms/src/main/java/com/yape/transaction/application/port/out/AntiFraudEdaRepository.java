package com.yape.transaction.application.port.out;

import com.yape.transaction.domain.Transaction;

public interface AntiFraudEdaRepository {
    void validate(Transaction transaction);
}