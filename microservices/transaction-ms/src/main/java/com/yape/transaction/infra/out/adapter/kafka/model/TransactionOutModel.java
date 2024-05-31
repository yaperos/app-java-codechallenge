package com.yape.transaction.infra.out.adapter.kafka.model;

import com.yape.transaction.domain.Transaction;
import com.yape.transaction.domain.type.TransactionStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionOutModel implements Serializable {
    private Long id;
    private UUID code;
    private TransactionStatusEnum status;
    private BigDecimal value;

    public static TransactionOutModel fromDomain(Transaction transaction) {
        return TransactionOutModel.builder()
                .id(transaction.getId())
                .code(transaction.getCode())
                .status(transaction.getStatus())
                .value(transaction.getValue())
                .build();
    }

    }
