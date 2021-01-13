package com.tools.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ArraysUtils {
    public static int[] getlastEntries(int[] array, int numberOfEntries) {
        return Arrays.copyOfRange(array, array.length - numberOfEntries, array.length);
    }

    public static int findSumUsingStream(int[] array) {
        return Arrays.stream(array).sum();
    }

    public static double getRecordsAverage(int[] records) {
        return MathUtils.formatDoubleToTwoDecimals((double)findSumUsingStream(records) / records.length);
    }

    public static double getRecordsAverageIncrease(int[] records) {
        List<Integer> recordsIncreases = getRecordsIncreases(records);
        int sumOfIncresease = recordsIncreases.stream().mapToInt(Integer::intValue).sum();
        double averageDifference = MathUtils.formatDoubleToTwoDecimals((double)sumOfIncresease / recordsIncreases.size());
        if (sumOfIncresease < 0) {
            int remainingDays = (int)(((records[records.length - 1]) / averageDifference) * (-1));
            System.out.println("GOOD NEWS!!! BASED ON " + averageDifference + " DAILY NEGATIVE INCREASE, THE CASES NUMBER SHOULD COME TO 0 IN ABOUT "
                    + remainingDays
                    + " DAYS");
        }
        return averageDifference;
    }

    public static List<Integer> getRecordsIncreases(int[] records) {
        List<Integer> recordsIncreases = new ArrayList<Integer>();
        for (int i = 1; i < records.length; i++) {
            recordsIncreases.add(records[i] - records[i - 1]);
        }
        return recordsIncreases;
    }

    public static double getLastRecordValuePercentageComparedToAverage(int[] records) {
        return MathUtils.formatDoubleToTwoDecimals((records[records.length - 1] / ArraysUtils.getRecordsAverage(records)) * 100);
    }

    public static double getLastRecordValuePercentageComparedToMax(int[] records) {
        return MathUtils.formatDoubleToTwoDecimals(((double)records[records.length - 1]
                / IntStream.range(0, records.length).map(i -> records[i]).max().getAsInt()) * 100);
    }

    public static double getLastRecordValuePercentageComparedToMin(int[] records) {
        return MathUtils.formatDoubleToTwoDecimals(((double)records[records.length - 1]
                / IntStream.range(0, records.length).map(i -> records[i]).min().getAsInt()) * 100);
    }
}
