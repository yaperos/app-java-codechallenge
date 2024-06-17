package com.yape.transaction.dtos;

import java.math.BigDecimal;
import java.util.UUID;

import com.yape.transaction.entities.TransactionStatus;
import com.yape.transaction.entities.TransactionType;
import com.yape.transaction.entities.YapeTransaction;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class YapeTransactionSingleRetrieval {
    private UUID transactionExternalId;
    private UUID accountExternalIdDebit;
    private UUID accountExternalIdCredit;
    private TransactionType transactionType;
    private BigDecimal value;
    private TransactionStatus transactionStatus;
    private String createdAt;

    public YapeTransactionSingleRetrieval (YapeTransaction transaction) {
        this.accountExternalIdDebit = transaction.getAccountExternalIdDebit();
        this.accountExternalIdCredit = transaction.getAccountExternalIdCredit();
        this.transactionExternalId = transaction.getTransactionExternalId();
        this.transactionType = transaction.getTransactionType();
        this.value = transaction.getValue();
        this.transactionStatus = transaction.getTransactionStatus();
        this.createdAt = transaction.getCreatedAt();
    }
}
