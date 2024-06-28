package com.yaperos.dto;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TransactionDto {
    private UUID accountExternalIdDebit;
    private UUID accountExternalIdCredit;
    private int transferTypeId;
    private double value;
}
