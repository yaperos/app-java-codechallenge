package com.yape.fraudevaluation.model.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionStatusEnum {

    PENDING(1, "PENDING"),
    APPROBED(2, "APPROBED"),
    REJECTED(3, "REJECTED")
    ;

    private final Integer value;
    private final String description;

}
