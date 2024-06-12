package com.yape.antifraud.infrastructure.models;

import com.yape.antifraud.usecases.models.AntifraudValidateModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class AntifraudMessageModel {
    private String transactionExternalId;
    private Double value;
    private String status;

    public AntifraudValidateModel getUseCaseModel() {
        return AntifraudValidateModel.builder()
                .transactionExternalId(UUID.fromString(transactionExternalId))
                .value(value)
                .status(status)
                .build();
    }
}
