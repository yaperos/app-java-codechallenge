package com.yape.transactionbff.infra.in.adapter.graphql.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionModelRequest {
    private UUID accountExternalIdDebit;
    private UUID accountExternalIdCredit;
    private Integer transferTypeId;
    private BigDecimal value;
}
