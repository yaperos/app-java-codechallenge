package com.yape.transaction.infrastructure.models;

import com.yape.transaction.entities.models.Transaction;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class AntifraudMessageModel implements Serializable {
    private String transactionExternalId;
    private Double value;
    private String status;

    public static AntifraudMessageModel getInstanceFrom(Transaction transaction) {
        return AntifraudMessageModel.builder()
                .transactionExternalId(transaction.getTransactionExternalId().toString())
                .status(transaction.getTransactionStatus().name())
                .value(transaction.getValue())
                .build();
    }
}
