package com.yape.codechallenge.transactionmanagementservice.infra.utils;

import com.yape.codechallenge.transactionmanagementservice.domain.TransactionStatus;
import com.yape.codechallenge.transactionmanagementservice.domain.TransactionType;
import com.yape.codechallenge.transactionmanagementservice.domain.Transactions;

import java.util.Map;

public class MapTransformUtils {
    private MapTransformUtils() {
    }
    public static Transactions map2Transactions(Map<String, Object> map) {
        return Transactions.builder()
                .transactionExternalId((String) map.get("transactionexternalid"))
                .value(ConvertUtils.getBigDecimalValueFromMap((Map<String, Object>) map.get("value")))
                .transactionType(TransactionType.valueOf(map.get("transactiontype").toString()))
                .transactionStatus(TransactionStatus.valueOf(map.get("transactionstatus").toString()))
                .createdAt(ConvertUtils.unixLongToLocalDateTime((long)map.get("createdat")))
                .build();
    }
}
