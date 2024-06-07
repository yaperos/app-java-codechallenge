package com.yape.fraudevaluation.kafka.producer;

import com.yape.fraudevaluation.model.dto.TransactionDTO;

public interface FraudMessageProducer {

    boolean sendMessage(TransactionDTO transactionDTO);

}
