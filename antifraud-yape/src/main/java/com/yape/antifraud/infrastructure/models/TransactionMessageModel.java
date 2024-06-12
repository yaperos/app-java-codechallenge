package com.yape.antifraud.infrastructure.models;

import com.yape.antifraud.entities.models.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionMessageModel {
    private String transactionExternalId;
    private String status;

    public static TransactionMessageModel getInstanceFrom(Transaction transaction) {
        return TransactionMessageModel.builder()
                .transactionExternalId(transaction.getTransactionExternalId().toString())
                .status(transaction.getTransactionStatus().name())
                .build();
    }
}
