package com.yape.transaction.infra.in.adapter.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.yape.transaction.domain.Transaction;
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

    public static TransactionModelResponse fromDomain(Transaction transaction) {
        return TransactionModelResponse.builder()
                .transactionExternalId(transaction.getCode())
                .transactionType(TransactionType.builder().name(transaction.getType().name()).build())
                .transactionStatus(TransactionStatus.builder().name(transaction.getStatus().name()).build())
                .value(transaction.getValue())
                .createAt(transaction.getCreateAt())
                .build();
    }

}
