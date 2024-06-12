package com.yape.transaction.usecases.models;

import com.yape.transaction.entities.enums.TransferType;
import com.yape.transaction.entities.models.Transaction;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CreateTransactionModel {
    UUID accountExternalIdDebit;
    UUID accountExternalIdCredit;
    Double value;
    Integer transferTypeId;

    public Transaction convertEntity() {
        return Transaction.builder()
                .accountExternalIdDebit(this.getAccountExternalIdDebit())
                .accountExternalIdCredit(this.getAccountExternalIdCredit())
                .value(this.getValue())
                .transferType(TransferType.valueOf(this.getTransferTypeId()).orElse(null))
                .build();
    }
}
