package com.yape.antifraud.application.port.out;

import com.yape.antifraud.infra.out.adapter.kafka.model.TransactionOutModel;

public interface TransactionEdaRepository {
    void updateTransaction(TransactionOutModel transactionModel);
}
