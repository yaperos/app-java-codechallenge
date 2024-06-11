package com.yape.challenge.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJSON(Object object){
        try {
            return objectMapper.writeValueAsString(object);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJSON(String json,Class<T> clazz){
        try {
            return objectMapper.readValue(json,clazz);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

}
