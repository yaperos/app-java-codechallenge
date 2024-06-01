package com.yape.transaction.validator.domain.event;

import com.yape.transaction.validator.domain.Transaction;

public interface ValidatedTransactionEventPort {

    void sendValidatedTransactionEvent (Transaction transaction);
}
