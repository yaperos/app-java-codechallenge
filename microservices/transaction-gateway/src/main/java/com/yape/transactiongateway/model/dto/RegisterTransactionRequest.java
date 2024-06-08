package com.yape.transactiongateway.model.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RegisterTransactionRequest {

    private String accountExternalIdDebit;
    private String accountExternalIdCredit;
    private Integer tranferTypeId;
    private BigDecimal value;

}
