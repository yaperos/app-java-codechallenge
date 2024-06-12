package com.yape.transaction.usecases.models;

import com.yape.transaction.entities.enums.TransactionStatus;
import com.yape.transaction.entities.models.Transaction;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UpdateTransactionModel {
    private UUID transactionExternalId;
    private String status;

    public Transaction convertEntity() {
        return Transaction.builder()
                .transactionExternalId(this.getTransactionExternalId())
                .transactionStatus(TransactionStatus.valueOf(this.getStatus()))
                .build();
    }
}
