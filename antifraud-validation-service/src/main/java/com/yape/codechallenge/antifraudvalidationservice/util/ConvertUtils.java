package com.yape.codechallenge.antifraudvalidationservice.util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yape.codechallenge.antifraudvalidationservice.event.IncomingCreationEvent;
import com.yape.codechallenge.antifraudvalidationservice.event.OutComingUpdatingEvent;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class ConvertUtils {
    private ConvertUtils() {
    }
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static Map<String, Object> convertJsonStringToMap(String jsonString) {
        return Optional.ofNullable(jsonString)
                .map(ConvertUtils::tryConvertJsonToMap)
                .orElseGet(ConvertUtils::createEmptyMap);
    }

    private static Map<String, Object> tryConvertJsonToMap(String jsonString) {
        try {
            return objectMapper.readValue(jsonString, Map.class);
        } catch (JsonProcessingException e) {
            log.error("Error while converting JSON to Map", e);
            return createEmptyMap();
        }
    }

    private static Map<String, Object> createEmptyMap() {
        return new HashMap<>();
    }
    public static String outcomingEventToJsonString(OutComingUpdatingEvent outComingUpdatingEvent) {
        try {
            return objectMapper.writeValueAsString(outComingUpdatingEvent);
        } catch (JsonProcessingException e) {
            log.error("Error while converting OutComingUpdatingEvent to JSON", e);
            return null;
        }
    }

    public static IncomingCreationEvent messageMapToTransactionEvent(Map<String, Object> messageMap) {
        Map<String, Object> payload = (Map<String, Object>) messageMap.get(ConstantsUtils.TransactionMapper.MAP_PAYLOAD);
        Map<String, Object> messagePayload = (Map<String, Object>) payload.get(ConstantsUtils.TransactionMapper.MAP_AFTER);

        IncomingCreationEvent incomingCreationEvent = new IncomingCreationEvent();
        incomingCreationEvent.setTransactionExternalId((String) messagePayload.get(ConstantsUtils.TransactionMapper.MAP_TRANSACTION_EXTERNAL_ID));
        incomingCreationEvent.setTransactionReceiptStatus((String) messagePayload.get(ConstantsUtils.TransactionMapper.MAP_TRANSACTION_STATUS));
        incomingCreationEvent.setTransactionReceiptValue(getBigDecimalValueFromMap((Map<String, Object>) messagePayload.get(ConstantsUtils.TransactionMapper.MAP_VALUE)));
        incomingCreationEvent.setOperationType(payload.get(ConstantsUtils.TransactionMapper.MAP_OP).toString());
        incomingCreationEvent.setOrigin((String) ((Map<String, Object>) payload.get(ConstantsUtils.TransactionMapper.MAP_SOURCE)).get(ConstantsUtils.TransactionMapper.MAP_TABLE));
        return incomingCreationEvent;
    }

    private static BigDecimal getBigDecimalValueFromMap(Map<String, Object> base64Valuemap) {
        return new BigDecimal(new BigInteger(Base64.getDecoder().decode(base64Valuemap.get(ConstantsUtils.TransactionMapper.MAP_VALUE).toString())), (Integer) base64Valuemap.get(ConstantsUtils.TransactionMapper.MAP_SCALE));
    }
}
