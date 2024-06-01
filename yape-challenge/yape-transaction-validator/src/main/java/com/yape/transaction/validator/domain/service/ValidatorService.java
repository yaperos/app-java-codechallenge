package com.yape.transaction.validator.domain.service;

import com.yape.transaction.validator.domain.Transaction;

public interface ValidatorService {

    void validateTransaction(Transaction transaction);

}
