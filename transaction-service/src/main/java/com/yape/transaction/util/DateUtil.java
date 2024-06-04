package com.yape.transaction.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtil {
  public static final String DATE_TIME_AMPM_FORMAT = "dd/MM/yyyy hh:mm:ss";

  public static String parseDateToString(LocalDateTime date) {
    return date != null ? date.format(DateTimeFormatter.ofPattern(DATE_TIME_AMPM_FORMAT)) : null;
  }
}
