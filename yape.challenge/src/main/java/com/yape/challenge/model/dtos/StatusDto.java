package com.yape.challenge.model.dtos;

import lombok.Data;

@Data
public class StatusDto {
    private String name;

    public StatusDto(String name) {
        this.name = name;
    }


}
