package com.yape.transaction.infrastructure.models;

import com.yape.transaction.usecases.models.UpdateTransactionModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionMessageModel {
    private String transactionExternalId;
    private String status;

    public UpdateTransactionModel getUseCaseModel() {
        return UpdateTransactionModel.builder()
                .transactionExternalId(UUID.fromString(transactionExternalId))
                .status(status)
                .build();
    }
}
