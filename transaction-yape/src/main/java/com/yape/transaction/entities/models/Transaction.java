package com.yape.transaction.entities.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.yape.transaction.entities.enums.TransactionStatus;
import com.yape.transaction.entities.enums.TransactionType;
import com.yape.transaction.entities.enums.TransferType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction implements Serializable {
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