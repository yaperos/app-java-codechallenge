package com.yape.antifraud.infra.in.adapter.kafka.model;

import com.yape.antifraud.domain.TransactionStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransactionInModel implements Serializable {
   private Long id;
   private UUID code;
   private TransactionStatusEnum status;
   private BigDecimal value;

}
