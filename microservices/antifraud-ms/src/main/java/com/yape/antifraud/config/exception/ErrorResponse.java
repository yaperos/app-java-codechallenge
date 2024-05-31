package com.yape.antifraud.config.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class ErrorResponse {
    @JsonProperty
    int errorInternalCode;
    @JsonProperty
    String errorDescription;
    @JsonProperty
    String errorCode;
}

