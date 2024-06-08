package com.yape.transactiongateway.model.dto;

import com.yape.transactiongateway.model.common.TransactionState;
import com.yape.transactiongateway.model.common.TransactionType;
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
public class TransactionResponse implements Serializable {

    private String transactionExternalId;
    private TransactionType transactionType;
    private TransactionState transactionStatus;
    private BigDecimal value;
    private String createdAt;

}
