package com.yape.transaction.dtos;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Data;

@Data
public class YapeTransactionInsertion {
    private UUID accountExternalIdDebit;
    private UUID accountExternalIdCredit;
    private int transferTypeId;
    private BigDecimal value;

    public YapeTransactionInsertion(UUID accountExternalIdDebit, UUID accountExternalIdCredit, int transferTypeId, BigDecimal value) {
        this.accountExternalIdDebit = accountExternalIdDebit;
        this.accountExternalIdCredit = accountExternalIdCredit;
        this.transferTypeId = transferTypeId;
        this.value = value;
    }
}
