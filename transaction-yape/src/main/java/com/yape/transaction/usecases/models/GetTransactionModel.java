package com.yape.transaction.usecases.models;

import com.yape.transaction.entities.models.Transaction;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class GetTransactionModel {
    UUID transactionExternalId;

    public Transaction convertEntity() {
        return Transaction.builder()
                .transactionExternalId(this.getTransactionExternalId())
                .build();
    }
}
