package com.yape.transaction.infrastructure.models;

import com.yape.transaction.usecases.models.UpdateTransactionModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateTransactionRequestModel {
    private String status;

    public UpdateTransactionModel getUseCaseModel() {
        return UpdateTransactionModel.builder().status(status).build();
    }
}