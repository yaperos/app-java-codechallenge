package com.yape.codechallenge.antifraudvalidationservice.service;

import com.yape.codechallenge.antifraudvalidationservice.event.IncomingCreationEvent;
import com.yape.codechallenge.antifraudvalidationservice.event.OutComingUpdatingEvent;
import com.yape.codechallenge.antifraudvalidationservice.util.ConstantsUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AntiFraudValidationService {
    public OutComingUpdatingEvent evaluateTransactionFraudRisk(IncomingCreationEvent incomingCreationEvent){
        return new OutComingUpdatingEvent(incomingCreationEvent.getTransactionExternalId(), ConstantsUtils.TRANSACTION_DB_OPERATION_CREATE.equals(incomingCreationEvent.getOperationType())
                && incomingCreationEvent.getTransactionReceiptValue().compareTo(new BigDecimal(1000)) < 0? ConstantsUtils.TRANSACTION_STATUS_ACCEPTED : ConstantsUtils.TRANSACTION_STATUS_REJECTED);
    }
}