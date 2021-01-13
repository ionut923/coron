package com.tools.utils;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.util.HashMap;
import java.util.Map;

public class DateUtils {

    public static LocalDate getCurrentDate() {
        LocalDate today = LocalDate.now();
        return today;
    }

    public static int getNumberOfDaysBetween(LocalDate startDate, LocalDate endDate) {
        return Days.daysBetween(startDate, endDate).getDays();
    }

    public static int getNumberOfDaysSincePresent(LocalDate startDate) {
        return Days.daysBetween(startDate, getCurrentDate()).getDays();
    }

    public static String[] getLastDays(int numberOfDays, String dateFormat) {
        String[] lastDays = new String[numberOfDays];
        for (int i = 0; i <= lastDays.length - 1; i++) {
            String dateString = LocalDate.now().minusDays(numberOfDays - i).toString(dateFormat);
            lastDays[i] = dateString;
        }
        return lastDays;
    }

    public static Map<String, Integer> getDaysMap(int[] values) {
        Map<String, Integer> daysRecords = new HashMap<String, Integer>();
        return daysRecords;
    }
}
