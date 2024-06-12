package com.yape.transaction.infrastructure.models;

import com.yape.transaction.entities.models.Transaction;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class GetTransactionResponseModel {

    private UUID transactionExternalId;
    private Double value;
    private String createdAt;
    private TransactionType transactionType;
    private TransactionStatus transactionStatus;
    @Data
    @Builder
    static class TransactionType {
        private String name;
    }
    @Data
    @Builder
    static class TransactionStatus {
        private String name;
    }

    public static GetTransactionResponseModel convertFrom(Transaction transaction) {
        return GetTransactionResponseModel.builder()
                    .transactionExternalId(transaction.getTransactionExternalId())
                    .value(transaction.getValue())
                    .createdAt(transaction.getCreateAt() == null ? null : transaction.getCreateAt().toString())
                    .transactionType(transaction.getTransactionType() == null ? null :
                            TransactionType.builder().name(transaction.getTransactionType().name()).build())
                    .transactionStatus(transaction.getTransactionStatus() == null ? null :
                            TransactionStatus.builder().name(transaction.getTransactionStatus().name()).build()).
                build();
    }
}
