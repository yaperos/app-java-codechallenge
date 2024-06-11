package com.yape.challenge.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum TransferStatus {
    PENDING(1L,"PENDING"),
    APPROVED(2L,"APPROVED"),
    REJECTED(3L,"REJECTED");

    private Long typeId;
    private String name;
    private static final Map<String, TransferStatus> MAP = new HashMap<>();

    TransferStatus(Long typeId, String name) {
        this.typeId = typeId;
        this.name = name;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public static TransferStatus fromName(String name){
        return MAP.get(name);
    }
    static{
        for(TransferStatus n : values()){
            MAP.put(n.getName(), n);
        }
    }

}
