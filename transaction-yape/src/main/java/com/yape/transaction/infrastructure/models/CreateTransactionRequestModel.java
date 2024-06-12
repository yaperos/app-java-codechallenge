package com.yape.transaction.infrastructure.models;

import com.yape.transaction.usecases.models.CreateTransactionModel;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class CreateTransactionRequestModel {

    @Pattern(regexp="^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$", message = "Not a valid UUID")
    @NotEmpty(message = "Must not be empty")
    private String accountExternalIdDebit;

    @Pattern(regexp="^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$", message = "Not a valid UUID")
    @NotEmpty(message = "Must not be empty")
    private String accountExternalIdCredit;

    @NotNull(message = "Must not be empty")
    @Min(value = 0L, message = "The value must be positive")
    private Double value;

    @NotNull(message = "Must not be empty")
    private Integer transferTypeId;

    public CreateTransactionModel getUseCaseModel() {
        return CreateTransactionModel.builder()
                .accountExternalIdDebit(UUID.fromString(accountExternalIdDebit))
                .accountExternalIdCredit(UUID.fromString(accountExternalIdCredit))
                .value(value)
                .transferTypeId(transferTypeId)
                .build();
    }


}