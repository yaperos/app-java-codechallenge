package com.yape.transaction.config.exception;

public class KafkaException extends GenericException {
    public KafkaException(ErrorCode ec){
        super(ec);
    }
}
