package com.yape.transaction.manager.domain.contants;

public enum TransactionTypeEnum {
    PAYMENT(1, "PAYMENT"),
    TRANSACTION(2, "TRANSACTION");

    public final int id;
    public final String description;

    TransactionTypeEnum(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public static TransactionTypeEnum valueOf (int id){
        for(TransactionTypeEnum e : values()) {
            if(e.id == id) {
                return e;
            }
        }

        return null;
    }
}
