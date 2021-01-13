package com.tools.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MapUtils {
    public static Map.Entry<String, Integer> getMinEntry(Map<String, Integer> map) {
        return Collections.min(map.entrySet(),
                Comparator.comparing(Entry::getValue));
    }

    public static Map.Entry<String, Integer> getMaxEntry(Map<String, Integer> map) {
        return Collections.max(map.entrySet(),
                Comparator.comparing(Entry::getValue));
    }

    public static double getRecordsAverage(Map<String, Integer> map) {
        return MathUtils.formatDoubleToTwoDecimals(map.entrySet().stream().mapToInt(i -> i.getValue()).average().orElse(0));
    }

    public static List<String> getMapKeys(Map<String, Integer> map) {
        Set<String> keySet = map.keySet();
        List<String> listKeys = new ArrayList<String>(keySet);
        return listKeys;
    }

    public static double getRecordsAverageIncrease(Map<String, Integer> map) {
        return map.values().stream().mapToDouble(Integer::doubleValue).average().orElse(0);
    }

    public static double getRecordsWeeklyAverageIncrease(Map<String, Integer> map, int numberOfWeeks) {
        return map.values().stream().mapToDouble(Integer::doubleValue).average().orElse(0);
    }

    public static void printNumberOfDaysUntilZeroCases(Map<String, Integer> map, double averageDifference) {
        List<String> listKeys = getMapKeys(map);
        int remainingDays = (int)(((map.get(listKeys.get(listKeys.size() - 1))) / averageDifference) * (-1));
        System.out.println("GOOD NEWS!!! BASED ON " + averageDifference + " DAILY NEGATIVE INCREASE, THE CASES SHOULD COME TO 0 IN ABOUT "
                + remainingDays
                + " DAYS");
    }

    public static int getLastRecordValue(Map<String, Integer> map) {
        return map.get(map.size() - 1);
    }

    public static double getLastRecordValuePercentageComparedToAverage(Map<String, Integer> map) {
        return MathUtils.formatDoubleToTwoDecimals((getLastRecordValue(map) / MapUtils.getRecordsAverage(map)) * 100);
    }

    public static void printEachDayRecordPercentageComparedToAverage(Map<String, Integer> map) {
        map.forEach((k, v) ->
        System.out.println(k + " value " + v + " represents "
                + MathUtils.formatDoubleToTwoDecimals((v / MapUtils.getRecordsAverage(map)) * 100) + "% of average"));
    }

    public static double getLastRecordValuePercentageComparedToMax(Map<String, Integer> map) {
        return MathUtils.formatDoubleToTwoDecimals(((double)getLastRecordValue(map) / getMaxEntry(map).getValue()) * 100);
    }

    public static void printEachDayRecordPercentageComparedToMax(Map<String, Integer> map) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " value " + entry.getValue() + " represents "
                    + MathUtils.formatDoubleToTwoDecimals(((double)entry.getValue() / getMaxEntry(map).getValue()) * 100) + "% of maximum");
        }
    }
}
