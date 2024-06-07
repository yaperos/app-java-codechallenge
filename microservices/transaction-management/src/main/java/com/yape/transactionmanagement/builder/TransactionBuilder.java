package com.yape.transactionmanagement.builder;

import com.yape.transactionmanagement.model.common.TransactionState;
import com.yape.transactionmanagement.model.common.TransactionType;
import com.yape.transactionmanagement.model.constant.TransactionStatusEnum;
import com.yape.transactionmanagement.model.constant.TransactionTypeEnum;
import com.yape.transactionmanagement.model.dto.TransactionDTO;
import com.yape.transactionmanagement.model.request.RegisterTransactionRequest;
import com.yape.transactionmanagement.model.response.TransactionResponse;
import com.yape.transactionmanagement.model.entity.TransactionEntity;
import com.yape.transactionmanagement.util.Utils;

import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionBuilder {

    public static TransactionResponse buildTransactionResponse(TransactionEntity entity) {
        return TransactionResponse.builder()
            .transactionExternalId(entity.getTransactionCode())
            .transactionType(TransactionType.builder()
                .name(entity.getTransactionType()).build())
            .transactionStatus(TransactionState.builder()
                .name(entity.getTransactionStatus()).build())
            .value(entity.getTransactionValue())
            .createdAt(Utils.formatDateTime(entity.getCreatedAt()))
            .build();
    }

    public static TransactionEntity buildTransactionEntity(RegisterTransactionRequest request) {
        return TransactionEntity.builder()
            .transactionCode(UUID.randomUUID().toString())
            .accountExternalIdDebit(request.getAccountExternalIdDebit())
            .accountExternalIdCredit(request.getAccountExternalIdCredit())
            .transactionType(TransactionTypeEnum.parse(request.getTranferTypeId()).getDescription())
            .transactionStatus(TransactionStatusEnum.PENDING.getDescription())
            .transactionValue(request.getValue())
            .createdAt(LocalDateTime.now())
            .build();
    }

    public static TransactionDTO buildTransactionDTO(TransactionEntity entity) {
        return TransactionDTO.builder()
            .transactionId(entity.getTransactionId())
            .transactionCode(entity.getTransactionCode())
            .transactionState(entity.getTransactionStatus())
            .transactionValue(entity.getTransactionValue())
            .build();
    }


}
