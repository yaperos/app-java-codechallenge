package com.yape.codechallenge.transactionmanagementservice.infra.utils;

import com.yape.codechallenge.transactionmanagementservice.domain.TransactionStatus;
import com.yape.codechallenge.transactionmanagementservice.domain.TransactionType;
import com.yape.codechallenge.transactionmanagementservice.domain.Transactions;

import java.util.Map;

public class MapTransformUtils {
    private MapTransformUtils() {
    }
    public static Transactions mapToTransaction(Map<String, Object> map) {
        return Transactions.builder()
                .transactionExternalId((String) map.get(ConstantsUtils.TransactionMapper.TRANSACTION_EXTERNAL_ID))
                .value(ConvertUtils.getBigDecimalValueFromMap((Map<String, Object>) map.get(ConstantsUtils.TransactionMapper.VALUE)))
                .transactionType(TransactionType.valueOf(map.get(ConstantsUtils.TransactionMapper.TRANSACTION_TYPE).toString()))
                .transactionStatus(TransactionStatus.valueOf(map.get(ConstantsUtils.TransactionMapper.TRANSACTION_STATUS).toString()))
                .createdAt(ConvertUtils.unixLongToLocalDateTime((long)map.get(ConstantsUtils.TransactionMapper.CREATED_AT)))
                .build();
    }
}
