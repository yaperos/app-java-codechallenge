package com.yape.antifraud.usecases.models;

import com.yape.antifraud.entities.enums.TransactionStatus;
import com.yape.antifraud.entities.models.Transaction;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AntifraudValidateModel {
    private UUID transactionExternalId;
    private Double value;
    private String status;

    public Transaction convertEntity() {
        return Transaction.builder()
                .transactionExternalId(this.getTransactionExternalId())
                .value(this.getValue())
                .transactionStatus(TransactionStatus.valueOf(this.getStatus()))
                .build();
    }
}
