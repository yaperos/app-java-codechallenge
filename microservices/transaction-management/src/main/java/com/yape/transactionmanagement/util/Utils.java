package com.yape.transactionmanagement.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMAT));
    }

}
