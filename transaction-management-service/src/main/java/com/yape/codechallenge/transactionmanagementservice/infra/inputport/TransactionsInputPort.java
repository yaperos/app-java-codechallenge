package com.yape.codechallenge.transactionmanagementservice.infra.inputport;

import com.yape.codechallenge.transactionmanagementservice.domain.Transactions;

import java.math.BigDecimal;


public interface TransactionsInputPort {

    public Transactions createTransaction(String externalIdDebit, String externalIdCredit, int transferTypeId, BigDecimal value);
    public Transactions updateTransaction(String externalTransactionId, String status);
    public Transactions getTransactionById(String customerId);

}
