package com.yape.codechallenge.antifraudvalidationservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OutComingUpdatingEvent {
    private String transactionExternalId;
    private String evaluationResult;
}
