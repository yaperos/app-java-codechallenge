package com.yape.transactionbff.config.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class ErrorResponse {
    @JsonProperty
    Integer code;
    @JsonProperty
    String description;
}
