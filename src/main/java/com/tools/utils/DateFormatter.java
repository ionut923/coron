package com.tools.utils;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

public class DateFormatter {

    public static LocalDate parseStringIntoDate(String dateString, String pattern) {
        return LocalDate.parse(dateString, DateTimeFormat.forPattern(pattern));
    }
}
