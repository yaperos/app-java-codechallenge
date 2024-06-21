package com.yape.transactional.enums;

import lombok.Getter;

@Getter
public enum TransactionStatusEnum {
    PENDING(1, "pending"), APPROVED(2, "approved"), REJECTED(3, "rejected");

    private Integer code;
    private String description;

    private TransactionStatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
