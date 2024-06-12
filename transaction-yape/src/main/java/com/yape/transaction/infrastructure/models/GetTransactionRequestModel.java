package com.yape.transaction.infrastructure.models;

import com.yape.transaction.usecases.models.GetTransactionModel;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class GetTransactionRequestModel {

    @Pattern(regexp="^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$", message = "Not a valid UUID")
    @NotEmpty(message = "Must not be empty")
    private String transactionExternalId;

    public GetTransactionModel getUseCaseModel() {
        return GetTransactionModel.builder().transactionExternalId(UUID.fromString(transactionExternalId)).build();
    }
}
