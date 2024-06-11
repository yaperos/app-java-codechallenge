package com.yape.challenge.model.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class TransferDto {

    private String accountExternalIdDebit;
    private String accountExternalIdCredit;
    private String tranferTypeId;
    private double value;
    private String transferStatus;
    private Date createdAt;


}
