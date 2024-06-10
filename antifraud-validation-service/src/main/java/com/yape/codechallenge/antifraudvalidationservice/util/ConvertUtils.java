package com.yape.codechallenge.antifraudvalidationservice.util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yape.codechallenge.antifraudvalidationservice.event.IncomingCreationEvent;
import com.yape.codechallenge.antifraudvalidationservice.event.OutComingUpdatingEvent;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class ConvertUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();
    public static Map<String, Object> jsonstring2Map( String json ) {
        if ( json == null ) return new HashMap<String, Object>();

        try {
            return objectMapper.readValue( json, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new HashMap<String, Object>();
    }

    public static String outcomingEventToJsonString(OutComingUpdatingEvent outComingUpdatingEvent) {
        try {
            return objectMapper.writeValueAsString(outComingUpdatingEvent);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static IncomingCreationEvent messageMapToTransactionEvent(Map<String, Object> messageMap) {
        Map<String, Object> payload = (Map<String, Object>) messageMap.get("payload");
        Map<String, Object> messagePayload = (Map<String, Object>) payload.get("after");

        IncomingCreationEvent incomingCreationEvent = new IncomingCreationEvent();
        incomingCreationEvent.setTransactionExternalId((String) messagePayload.get("transactionexternalid"));
        incomingCreationEvent.setTransactionReceiptStatus((String) messagePayload.get("transactionstatus"));
        incomingCreationEvent.setTransactionReceiptValue(getBigDecimalValueFromMap((Map<String, Object>) messagePayload.get("value")));
        incomingCreationEvent.setOperationType(payload.get("op").toString());
        incomingCreationEvent.setOrigin((String) ((Map<String, Object>) payload.get("source")).get("table"));
        return incomingCreationEvent;
    }

    private static BigDecimal getBigDecimalValueFromMap(Map<String, Object> base64Valuemap) {
        return new BigDecimal(new BigInteger(Base64.getDecoder().decode(base64Valuemap.get("value").toString())), (Integer) base64Valuemap.get("scale"));
    }
}
