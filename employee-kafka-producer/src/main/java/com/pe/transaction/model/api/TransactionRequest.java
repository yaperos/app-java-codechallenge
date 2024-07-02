package com.pe.transaction.model.api;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString; 

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull; 
import javax.validation.constraints.Size;

 

@Getter
@Setter
@ToString
public class TransactionRequest {

    @NotNull
    private int value;

    @NotNull
    private int transferTypeId;

    @NotBlank
    @Size(min = 1, max = 50)
    private String accountExternalIdDebit;

    @NotBlank
    @Size(min = 1, max = 50)
    private String accountExternalIdCredit;





}
