package com.yape.codechallenge.antifraudvalidationservice.event;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IncomingCreationEvent {
    private String transactionExternalId;
    private String transactionReceiptStatus;
    private BigDecimal transactionReceiptValue;
    private String operationType;
    private String origin;
}
