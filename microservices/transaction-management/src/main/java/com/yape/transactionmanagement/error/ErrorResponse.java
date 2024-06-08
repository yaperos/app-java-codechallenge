package com.yape.transactionmanagement.error;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

    private Integer errorCode;
    private String description;

}
