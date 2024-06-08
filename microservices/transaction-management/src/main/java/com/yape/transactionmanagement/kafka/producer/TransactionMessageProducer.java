package com.yape.transactionmanagement.kafka.producer;

import com.yape.transactionmanagement.model.dto.TransactionDTO;

public interface TransactionMessageProducer {

    boolean sendMessage(TransactionDTO transactionDTO);

}
