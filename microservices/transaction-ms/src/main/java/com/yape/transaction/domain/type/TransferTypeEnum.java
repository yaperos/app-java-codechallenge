package com.yape.transaction.domain.type;

import java.util.Arrays;

public enum TransferTypeEnum {
    ONUS(1),
    INTERBANK(2);
    private final int value;
    TransferTypeEnum(int value){
        this.value = value;
    }
    public static TransferTypeEnum findByValue(int value) {
        return Arrays.stream(TransferTypeEnum.values())
                .filter(e -> e.value == value)
                .findAny()
                .orElseThrow(() -> new IllegalStateException(String.format("Unsupported type %s.", value)));
    }
}
