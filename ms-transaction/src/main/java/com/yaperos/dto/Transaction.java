package com.yaperos.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Transaction {
    @Id
    @GeneratedValue
    private UUID transactionExternalId;
    private UUID accountExternalIdDebit;
    private UUID accountExternalIdCredit;
    private int transferTypeId;
    private double value;
    private Date createdAt;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
}