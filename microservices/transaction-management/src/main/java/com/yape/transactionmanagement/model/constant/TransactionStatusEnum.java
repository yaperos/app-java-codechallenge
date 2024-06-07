package com.yape.transactionmanagement.model.constant;

import com.yape.transactionmanagement.error.NotFoundException;
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
