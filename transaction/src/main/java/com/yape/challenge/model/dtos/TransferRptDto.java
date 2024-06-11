package com.yape.challenge.model.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class TransferRptDto {
    private String accountExternalIdDebit;
    private String accountExternalIdCredit;
    private TypeDto transactionType;
    private double value;
    private StatusDto transferStatus;
    private Date createdAt;

    public TransferRptDto(String accountExternalIdDebit, String accountExternalIdCredit, TypeDto transactionType, StatusDto transferStatus, double value, Date createdAt) {
        this.accountExternalIdDebit = accountExternalIdDebit;
        this.accountExternalIdCredit = accountExternalIdCredit;
        this.transactionType = transactionType;
        this.value = value;
        this.transferStatus = transferStatus;
        this.createdAt = createdAt;
    }


}
