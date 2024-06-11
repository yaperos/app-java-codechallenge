package com.yape.codechallenge.transactionmanagementservice.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class Transactions {
    private String transactionExternalId;
    private String accountExternalIdDebit;
    private String accountExternalIdCredit;
    private int transferTypeId;
    private BigDecimal value;
    private TransactionType transactionType;
    private TransactionStatus transactionStatus;
    private LocalDateTime createdAt;

}