package com.yape.transaction.manager.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yape.transaction.manager.application.response.FindTransactionResponse;
import com.yape.transaction.manager.application.response.TransactionStatus;
import com.yape.transaction.manager.application.response.TransactionType;
import com.yape.transaction.manager.domain.contants.TransactionTypeEnum;
import lombok.Getter;

import java.util.Date;

@Getter
public class Transaction {

    private String internalId;
    private String transactionExternalId;
    private int transferTypeId;
    private String status;
    private double value;
    private Date createdAt;

    @JsonCreator
    public Transaction(@JsonProperty("accountExternalIdDebit") final String accountExternalIdDebit,
                       @JsonProperty("accountExternalIdCredit") final String accountExternalIdCredit,
                       @JsonProperty("tranferTypeId") final int tranferTypeId,
                       @JsonProperty("value") double value) {

        if (accountExternalIdDebit != null) {
            this.transactionExternalId = accountExternalIdDebit;
        } else if (accountExternalIdCredit != null) {
            this.transactionExternalId = accountExternalIdCredit;
        }

        this.transferTypeId = tranferTypeId;
        this.value = value;
        this.status = "PENDING";
        this.createdAt = new Date();
    }
    public Transaction(String internalId,
                       String transactionExternalId,
                       int transferTypeId,
                       String status,
                       double value,
                       Date createdAt) {
        this.internalId = internalId;
        this.transactionExternalId = transactionExternalId;
        this.transferTypeId = transferTypeId;
        this.status = status;
        this.value = value;
        this.createdAt = createdAt;
    }

    public FindTransactionResponse toRestFindResponse() {

        FindTransactionResponse response = new FindTransactionResponse();
        response.setTransactionExternalId(transactionExternalId);
        response.setTransactionStatus(new TransactionStatus(status));
        response.setTransactionType(new TransactionType(TransactionTypeEnum.valueOf(transferTypeId).description));
        response.setValue(value);
        response.setCreateAt(createdAt);
        return response;
    }
}
