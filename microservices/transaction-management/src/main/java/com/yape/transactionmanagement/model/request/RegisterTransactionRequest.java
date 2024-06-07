package com.yape.transactionmanagement.model.request;

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

    private String accountExternalIdDebit;
    private String accountExternalIdCredit;
    private Integer tranferTypeId;
    private BigDecimal value;

}
