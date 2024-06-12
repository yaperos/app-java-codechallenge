package com.yape.antifraud.infra.out.adapter.kafka.model;

import com.yape.antifraud.domain.TransactionStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionOutModel implements Serializable {
    private Long id;
    private TransactionStatusEnum status;
    private UUID code;
}
