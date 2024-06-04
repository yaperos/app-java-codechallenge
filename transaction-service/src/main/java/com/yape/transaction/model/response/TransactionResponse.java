package com.yape.transaction.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TransactionResponse {
    private String transactionExternalId;
    private ValueName transactionType;
    private ValueName transactionStatus;
    private Double value;
    private String createdAt;
}
