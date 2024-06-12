package com.yape.antifraud.entities.models;

import com.yape.antifraud.entities.enums.TransactionStatus;
import com.yape.antifraud.entities.enums.TransactionType;
import com.yape.antifraud.entities.enums.TransferType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class Transaction {
    private Long id;
    private UUID transactionExternalId;
    private UUID accountExternalIdDebit;
    private UUID accountExternalIdCredit;
    private Double value;
    private TransferType transferType;
    private TransactionType transactionType;
    private TransactionStatus transactionStatus;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}