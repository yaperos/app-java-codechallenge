package com.yape.transaction.config.exception;

import lombok.Getter;

public enum ErrorCode {
    KAFKA_EXCEPTION(109, "Kafka Error", "KAFKA_EXCEPTION"),
    INTERNAL_ERROR(108,"Internal Error","INTERNAL_ERROR"),
    TRANSACTION_NOT_FOUND(110,"Transaction Not Found","NOT_FOUND");

    private final int value;
    @Getter
    private final String reasonPhrase;
    @Getter
    private final String code;

    ErrorCode(int value, String reasonPhrase, String code) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
        this.code = code;
    }

    public int value() {
        return this.value;
    }

}
