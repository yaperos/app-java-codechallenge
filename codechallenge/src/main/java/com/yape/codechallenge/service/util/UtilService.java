package com.yape.codechallenge.service.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class UtilService {

    public String getSystemCreationDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SS");
        return now.format(formatter);
    }

    public String getOffsetDateTime() {
        Date date = new Date();
        Instant instant = date.toInstant();
        ZoneOffset zoneOffset = ZoneOffset.of("-05:00"); // Zona horaria de Lima
        var dateCreate = instant.atOffset(zoneOffset);
        return dateCreate.toString();
    }

}
