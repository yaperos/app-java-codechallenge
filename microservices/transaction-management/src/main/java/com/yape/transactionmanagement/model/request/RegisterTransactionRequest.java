package com.yape.transactionmanagement.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterTransactionRequest {

    @NotBlank(message = "El campo accountExternalIdDebit no puede enviarse vacio")
    private String accountExternalIdDebit;

    @NotBlank(message = "El campo accountExternalIdCredit no puede enviarse vacio")
    private String accountExternalIdCredit;

    @NotNull (message = "El campo tranferTypeId es obligatorio")
    private Integer tranferTypeId;

    @NotNull (message = "El campo tranferTypeId es obligatorio")
    private BigDecimal value;

}
