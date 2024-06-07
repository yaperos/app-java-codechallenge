package com.yape.fraudevaluation.kafka.consumer;

import com.yape.fraudevaluation.model.dto.TransactionDTO;

public interface FraudMessageConsumer {

    void retrieveMessage(TransactionDTO object);

}
