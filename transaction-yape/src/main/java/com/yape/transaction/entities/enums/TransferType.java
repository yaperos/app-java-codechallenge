package com.yape.transaction.entities.enums;

import java.util.Arrays;
import java.util.Optional;

public enum TransferType {
    NATIONAL,
    INTERNATIONAL;

    public static Optional<TransferType> valueOf(Integer value) {
        return Arrays.stream(values())
                .filter(transferType -> transferType.ordinal() == value)
                .findFirst();
    }
}
