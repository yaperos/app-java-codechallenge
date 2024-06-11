package com.yape.codechallenge.transactionmanagementservice.infra.inputport;

import com.yape.codechallenge.transactionmanagementservice.domain.Transactions;

import java.math.BigDecimal;


public interface TransactionsInputPort {
    Transactions createTransaction(String externalIdDebit, String externalIdCredit, int transferTypeId, BigDecimal value);
    Transactions updateTransaction(String externalTransactionId, String status);
}
