package com.yape.transactionbff.application.domain;

import com.yape.transactionbff.infra.in.adapter.graphql.model.TransactionStatus;
import com.yape.transactionbff.infra.in.adapter.graphql.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Value
@AllArgsConstructor
public class Transaction {
    UUID transactionExternalId;
    TransactionType transactionType;
    TransactionStatus transactionStatus;
    BigDecimal value;
    LocalDateTime createAt;
}
