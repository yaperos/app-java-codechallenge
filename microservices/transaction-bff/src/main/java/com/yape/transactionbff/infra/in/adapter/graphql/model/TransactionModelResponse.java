package com.yape.transactionbff.infra.in.adapter.graphql.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.yape.transactionbff.application.domain.Transaction;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionModelResponse {
    private UUID transactionExternalId;
    private TransactionType transactionType;
    private TransactionStatus transactionStatus;
    private BigDecimal value;
    private LocalDateTime createAt;

    public Transaction toDomain() {
        return Transaction.builder()
                .transactionExternalId(transactionExternalId)
                .transactionType(transactionType)
                .transactionStatus(transactionStatus)
                .value(value)
                .createAt(createAt)
                .build();
    }

    public static TransactionModelResponse fromDomain(Transaction transaction) {
        return TransactionModelResponse.builder()
                .transactionExternalId(transaction.getTransactionExternalId())
                .transactionType(transaction.getTransactionType())
                .transactionStatus(transaction.getTransactionStatus())
                .value(transaction.getValue())
                .createAt(transaction.getCreateAt())
                .build();
    }

}
