package com.yape.transaction.validator.domain.service;

import com.yape.transaction.validator.domain.Transaction;
import com.yape.transaction.validator.domain.event.ValidatedTransactionEventPort;

public class DomainValidatorService implements ValidatorService {

    private final ValidatedTransactionEventPort eventPort;

    public DomainValidatorService(ValidatedTransactionEventPort eventPort) {
        this.eventPort = eventPort;
    }

    @Override
    public void validateTransaction(Transaction transaction) {

        if(transaction.getValue() > 1000) {
            transaction.setStatus("REJECTED");
        } else {
            transaction.setStatus("APPROVED");
        }

        eventPort.sendValidatedTransactionEvent(transaction);
    }
}
