package com.yape.challenge.model.dtos;

import lombok.Data;

@Data
public class TypeDto {
    private String name;

    public TypeDto(String name) {
        this.name = name;
    }


}
