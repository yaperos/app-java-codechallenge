package com.yape.transaction.domain;

import com.yape.transaction.domain.type.TransactionStatusEnum;
import com.yape.transaction.domain.type.TransactionTypeEnum;
import com.yape.transaction.domain.type.TransferTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Value
@AllArgsConstructor
public class Transaction {
    Long id;
    UUID code;
    UUID accountExternalIdDebit;
    UUID accountExternalIdCredit;
    TransferTypeEnum transferType;
    TransactionTypeEnum type;
    TransactionStatusEnum status;

    BigDecimal value;
    LocalDateTime createAt;
    LocalDateTime updateAt;
}
