package com.yape.antifraud.config.exception;

public enum ErrorCode {

    BAD_REQUEST(105, "La request esta mal formateada", "BAD_REQUEST"),
    INVALID_PARAMETERS_ERROR(110, "{}", "INVALID_PARAMETERS"),
    WEB_CLIENT_GENERIC(103, "Unexpected rest client error", "INTERNAL_SERVER_ERROR"),
    KAFKA_EXCEPTION(109, "Error interno de kafka", "KAFKA_EXCEPTION"),
    INTERNAL_ERROR(108,"Internal Error","INTERNAL_ERROR"),
    INVALID_FILTERS_ERROR(109, "Invalid filters", "INVALID_FILTERS");

    private final int value;
    private final String reasonPhrase;
    private final String code;

    ErrorCode(int value, String reasonPhrase, String code) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
        this.code = code;
    }

    public int value() {
        return this.value;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    public String getCode() {
        return this.code;
    }
}
