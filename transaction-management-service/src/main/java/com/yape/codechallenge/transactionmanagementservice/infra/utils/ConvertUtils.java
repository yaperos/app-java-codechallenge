package com.yape.codechallenge.transactionmanagementservice.infra.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class ConvertUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    private ConvertUtils() {}

    public static String map2Jsonstring( Map<String, Object> map ) {
        if ( map == null ) return "{}";

        try {
            return objectMapper.writeValueAsString( map );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "{}";
    }

    public static Map<String, Object> jsonstring2Map( String json ) {
        if ( json == null ) return new HashMap<>();

        try {
            return objectMapper.readValue( json, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new HashMap<>();
    }

    public static LocalDateTime unixLongToLocalDateTime(long unixTimeSeg) {
        long unixTime = unixTimeSeg /1000;
        Instant instant = Instant.ofEpochMilli(unixTime);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static BigDecimal getBigDecimalValueFromMap(Map<String, Object> base64Valuemap) {
        return new BigDecimal(new BigInteger(Base64.getDecoder().decode(base64Valuemap.get("value").toString())), (Integer) base64Valuemap.get("scale"));
    }
}
