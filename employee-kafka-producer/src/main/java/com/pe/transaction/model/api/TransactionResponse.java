package com.pe.transaction.model.api;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Getter
@Setter
@ToString
public class TransactionResponse {

    @NotBlank
    @Size(min = 1, max = 50)
    private String transactionExternalId;

    private TransactionType transactionType;
    private TransactionType transactionStatus;


    @NotNull
    private int value;

    @NotBlank
    @Pattern(regexp = "YYYY/MM/DD HH:SS:MM")
    @Size(min = 1, max = 50)
    private String createdAt;
}