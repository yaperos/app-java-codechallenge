package com.yape.transactionmanagement.model.constant;

import com.yape.transactionmanagement.error.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionTypeEnum {

    OWN(1, "OWN"),
    THIRDPARTY(2, "THIRDPARTY"),
    INTERBANK(3, "EXTERNAL")
    ;

    private final Integer value;
    private final String description;

    public static TransactionTypeEnum parse(Integer value) {
        for (TransactionTypeEnum state : TransactionTypeEnum.values()) {
            if (value.equals(state.getValue())) return state;
        }
        throw new NotFoundException("Transaction type not found.");
    }

}
