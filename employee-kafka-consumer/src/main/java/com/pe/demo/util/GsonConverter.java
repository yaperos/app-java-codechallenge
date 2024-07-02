package com.pe.demo.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GsonConverter {

    private static final ObjectMapper objectMapper = new ObjectMapper();


    public static String convertToJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T readDataFromJson(
            String file, Class<T> className) {
        try {
            return objectMapper.readValue(file, className);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
