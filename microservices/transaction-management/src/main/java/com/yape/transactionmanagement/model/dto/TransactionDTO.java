package com.yape.transactionmanagement.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDTO implements Serializable {

    private Long transactionId;
    private String transactionCode;
    private String transactionState;
    private BigDecimal transactionValue;

}
