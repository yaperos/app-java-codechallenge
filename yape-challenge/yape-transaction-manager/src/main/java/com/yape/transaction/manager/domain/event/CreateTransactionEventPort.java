package com.yape.transaction.manager.domain.event;

import com.yape.transaction.manager.domain.Transaction;

public interface CreateTransactionEventPort {

    void sendCreateTransactionEvent (Transaction transaction);

}
