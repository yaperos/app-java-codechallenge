package com.yape.transaction.validator.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
public class Transaction {

    private String internalId;
    private String transactionExternalId;
    private int transferTypeId;
    private String status;
    private double value;
    private Date createdAt;
}
