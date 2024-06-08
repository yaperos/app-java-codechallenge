package com.yape.transactionmanagement.kafka.consumer;

import com.yape.transactionmanagement.model.dto.TransactionDTO;

public interface TransactionMessageConsumer {

    void retrieveMessage(TransactionDTO object);

}
