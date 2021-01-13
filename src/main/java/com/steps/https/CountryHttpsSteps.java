package com.steps.https;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import com.tools.constants.Constants;
import com.tools.utils.ArraysUtils;
import com.tools.utils.DateUtils;
import com.tools.utils.MapUtils;

public class CountryHttpsSteps extends AbstractHttpsSteps {

    private static final long serialVersionUID = 1L;
    @Steps()
    private HomeHttpSteps homeHttpSteps;

    public int[] getCountryDataValues(String countryName, int lastValueReference, int numberOfRetroDays) {
        return ArraysUtils.getlastEntries(getAllCountryDataValues(countryName, lastValueReference), numberOfRetroDays);
    }

    public int[] getAllCountryDataValues(String countryName, int lastValueReference) {
        String countryDetailsPage = getRequest("/country/" + countryName + "/").asString();
        String mydata = countryDetailsPage;
        Pattern pattern = Pattern.compile("\\[(.+?)," + lastValueReference + "\\]");
        Matcher matcher = pattern.matcher(mydata);
        matcher.find();
        String[] values = matcher.group().replaceAll("null", "0").replaceAll("\\[", "").replaceAll("\\]", "").split(",");
        int[] intValues = Arrays.stream(values).mapToInt(Integer::parseInt).toArray();
        return intValues;
    }

    public int[] getTotalCasesData(String countryName, int numberOfRetroDays) {
        return getCountryDataValues(countryName, homeHttpSteps.getCountryRecord(Constants.TAB_YESTERDAY, countryName).getTotalCases(), numberOfRetroDays);
    }

    public int[] getAllTotalCasesData(String countryName) {
        return getAllCountryDataValues(countryName, homeHttpSteps.getCountryRecord(Constants.TAB_YESTERDAY, countryName).getTotalCases());
    }

    public int[] getDailyNewCasesData(String countryName, int numberOfRetroDays) {
        return getCountryDataValues(countryName, homeHttpSteps.getCountryRecord(Constants.TAB_YESTERDAY, countryName).getNewCases(), numberOfRetroDays);
    }

    public int[] getAllDailyNewCasesData(String countryName) {
        return getAllCountryDataValues(countryName, homeHttpSteps.getCountryRecord(Constants.TAB_YESTERDAY, countryName).getNewCases());
    }

    public int[] getActiveCasesData(String countryName, int numberOfRetroDays) {
        return getCountryDataValues(countryName, homeHttpSteps.getCountryRecord(Constants.TAB_YESTERDAY, countryName).getActiveCases(), numberOfRetroDays);
    }

    public int[] getAllActiveCasesData(String countryName) {
        return getAllCountryDataValues(countryName, homeHttpSteps.getCountryRecord(Constants.TAB_YESTERDAY, countryName).getActiveCases());
    }

    public int[] getTotalDeathsData(String countryName, int numberOfRetroDays) {
        return getCountryDataValues(countryName, homeHttpSteps.getCountryRecord(Constants.TAB_YESTERDAY, countryName).getTotalDeaths(), numberOfRetroDays);
    }

    public int[] getDailyDeathsData(String countryName, int numberOfRetroDays) {
        return getCountryDataValues(countryName, homeHttpSteps.getCountryRecord(Constants.TAB_YESTERDAY, countryName).getNewDeaths(), numberOfRetroDays);
    }

    public int[] getAllDailyDeathsData(String countryName) {
        return getAllCountryDataValues(countryName, homeHttpSteps.getCountryRecord(Constants.TAB_YESTERDAY, countryName).getNewDeaths());
    }

    public void printDailyTotalCasesInformation(String countryName, int numberOfRetroDays) {
        int[] dailyTotalCases = getTotalCasesData(countryName, numberOfRetroDays);
        System.out.println("TOTAL CASES AVERAGE: " + ArraysUtils.getRecordsAverage(dailyTotalCases));
        System.out.println("TOTAL CASES AVERAGE DAILY INCREASE: " + ArraysUtils.getRecordsAverageIncrease(dailyTotalCases) + "\n");
    }

    public void printDailyActiveCasesInformation(String countryName, int numberOfRetroDays) {
        int[] dailyActiveCases = getActiveCasesData(countryName, numberOfRetroDays);
        System.out.println("ACTIVE CASES AVERAGE: " + ArraysUtils.getRecordsAverage(dailyActiveCases));
        System.out.println("ACTIVE CASES AVERAGE DAILY INCREASE: " + ArraysUtils.getRecordsAverageIncrease(dailyActiveCases));
        System.out.println("YESTERDAY ACTIVE CASES VS THE AVERAGE: " + ArraysUtils.getLastRecordValuePercentageComparedToAverage(dailyActiveCases) + "%");
        System.out.println("YESTERDAY ACTIVE CASES VS THE MAXIMUM: " + ArraysUtils.getLastRecordValuePercentageComparedToMax(dailyActiveCases) + "%");
        System.out.println("YESTERDAY ACTIVE CASES VS THE MINIMUM: " + ArraysUtils.getLastRecordValuePercentageComparedToMin(dailyActiveCases) + "%\n");
    }

    public void printDailyNewCasesInformation(String countryName, int numberOfRetroDays) {
        int[] dailyNewCases = getDailyNewCasesData(countryName, numberOfRetroDays);
        System.out.println("DAILY NEW CASES AVERAGE: " + ArraysUtils.getRecordsAverage(dailyNewCases));
        System.out.println("NEW CASES AVERAGE DAILY INCREASE: " + ArraysUtils.getRecordsAverageIncrease(dailyNewCases));
        System.out.println("YESTERDAY NEW CASES VS THE AVERAGE: " + ArraysUtils.getLastRecordValuePercentageComparedToAverage(dailyNewCases) + "%");
        System.out.println("YESTERDAY NEW CASES VS THE MAXIMUM: " + ArraysUtils.getLastRecordValuePercentageComparedToMax(dailyNewCases) + "%");
        System.out.println("YESTERDAY NEW CASES VS THE MINIMUM: " + ArraysUtils.getLastRecordValuePercentageComparedToMin(dailyNewCases) + "%\n");
    }

    public void printDailyTotalDeathsInformation(String countryName, int numberOfRetroDays) {
        int[] dailyTotalDeathsMap = getTotalDeathsData(countryName, numberOfRetroDays);
        System.out.println("TOTAL DEATHS AVERAGE: " + ArraysUtils.getRecordsAverage(dailyTotalDeathsMap));
        System.out.println("TOTAL DEATHS AVERAGE DAILY INCREASE: " + ArraysUtils.getRecordsAverageIncrease(dailyTotalDeathsMap) + "\n");
    }

    public void printDailyNewDeathsInformation(String countryName, int numberOfRetroDays) {
        int[] dailyNewDeaths = getDailyDeathsData(countryName, numberOfRetroDays);
        System.out.println("DAILY NEW DEATHS AVERAGE: " + ArraysUtils.getRecordsAverage(dailyNewDeaths));
        System.out.println("NEW DEATHS AVERAGE DAILY INCREASE: " + ArraysUtils.getRecordsAverageIncrease(dailyNewDeaths));
        System.out.println("YESTERDAY NEW DEATHS VS THE AVERAGE: " + ArraysUtils.getLastRecordValuePercentageComparedToAverage(dailyNewDeaths) + "%");
        System.out.println("YESTERDAY NEW DEATHS VS THE MAXIMUM: " + ArraysUtils.getLastRecordValuePercentageComparedToMax(dailyNewDeaths) + "%");
        System.out.println("YESTERDAY NEW DEATHS VS THE MINIMUM: " + ArraysUtils.getLastRecordValuePercentageComparedToMin(dailyNewDeaths) + "%\n");
    }

    @Step
    public void printCountryDailyInformation(String countryName, int numberOfRetroDays) {
        System.out.println(countryName + "'s DAILY INFORMATION FROM " + numberOfRetroDays + " LAST DAYS SITUATION:");
        printDailyTotalCasesInformation(countryName, numberOfRetroDays);
        printDailyActiveCasesInformation(countryName, numberOfRetroDays);
        printDailyNewCasesInformation(countryName, numberOfRetroDays);
        printDailyTotalDeathsInformation(countryName, numberOfRetroDays);
        printDailyNewDeathsInformation(countryName, numberOfRetroDays);
    }

    @Step
    public void printCountryDaysChunksInformation(String countryName, int numberOfRetroDays, int groupSize) {
        System.out.println(countryName + "'s DAILY INFORMATION FROM " + numberOfRetroDays + " LAST DAYS SITUATION:");
        printDailyTotalCasesInformation(countryName, numberOfRetroDays);
        printDailyActiveCasesInformation(countryName, numberOfRetroDays);
        printDailyNewCasesInformation(countryName, numberOfRetroDays);
        printDailyTotalDeathsInformation(countryName, numberOfRetroDays);
        printDailyNewDeathsInformation(countryName, numberOfRetroDays);
    }

    public static Map<String, Integer> getDaysAndValuesBasedOnData(int[] records) {
        Map<String, Integer> entries = new HashMap<String, Integer>();
        String[] days = DateUtils.getLastDays(records.length, "MMM dd");
        for (int i = 0; i < records.length; i++) {
            entries.put(days[i], records[i]);
        }
        return entries;
    }

    @Step
    public void printCountryBestAndWorstDaysInforrmation(String countryName, int numberOfRetroDays) {
        Map<String, Integer> dailyNewCasesMap = getDaysAndValuesBasedOnData(getDailyNewCasesData(countryName, numberOfRetroDays));
        Map.Entry<String, Integer> dailyNewCasesMinEntry = MapUtils.getMinEntry(dailyNewCasesMap);
        Map.Entry<String, Integer> dailyNewCasesMaxEntry = MapUtils.getMaxEntry(dailyNewCasesMap);
        Map<String, Integer> dailyNewDeathsMap = getDaysAndValuesBasedOnData(getDailyDeathsData(countryName, numberOfRetroDays));
        Map.Entry<String, Integer> dailyNewDeathsMinEntry = MapUtils.getMinEntry(dailyNewDeathsMap);
        Map.Entry<String, Integer> dailyNewDeathsMaxEntry = MapUtils.getMaxEntry(dailyNewDeathsMap);
        Map<String, Integer> activeCasesMap = getDaysAndValuesBasedOnData(getActiveCasesData(countryName, numberOfRetroDays));
        Map.Entry<String, Integer> activeCasesMinEntry = MapUtils.getMinEntry(activeCasesMap);
        Map.Entry<String, Integer> activeCasesMaxEntry = MapUtils.getMaxEntry(activeCasesMap);

        System.out.println(countryName + "'s BEST AND WORST SITUATIONS IN THE LAST " + numberOfRetroDays + " DAYS:");
        System.out.println("THE LOWEST NEW CASES NUMBER WAS ON " + dailyNewCasesMinEntry.getKey() + " WITH " + dailyNewCasesMinEntry.getValue()
                + " CASES");
        System.out.println("THE HIGHEST NEW CASES NUMBER WAS ON " + dailyNewCasesMaxEntry.getKey() + " WITH " + dailyNewCasesMaxEntry.getValue()
                + " CASES\n");

        System.out.println("THE LOWEST NEW DEATHS NUMBER WAS ON " + dailyNewDeathsMinEntry.getKey() + " WITH " + dailyNewDeathsMinEntry.getValue()
                + " CASES");
        System.out.println("THE HIGHEST NEW DEATHS NUMBER WAS ON " + dailyNewDeathsMaxEntry.getKey() + " WITH " + dailyNewDeathsMaxEntry.getValue()
                + " CASES\n");

        System.out.println("THE LOWEST ACTIVE CASES NUMBER WAS ON " + activeCasesMinEntry.getKey() + " WITH " + activeCasesMinEntry.getValue()
                + " CASES");
        System.out.println("THE HIGHEST ACTIVE CASES NUMBER WAS ON " + activeCasesMaxEntry.getKey() + " WITH " + activeCasesMaxEntry.getValue()
                + " CASES");
    }

    @Step
    public void printCountrySinceStartBestAndWorstDaysInforrmation(String countryName) {
        Map<String, Integer> dailyNewCasesMap = getDaysAndValuesBasedOnData(getAllDailyNewCasesData(countryName));
        Map.Entry<String, Integer> dailyNewCasesMinEntry = MapUtils.getMinEntry(dailyNewCasesMap);
        Map.Entry<String, Integer> dailyNewCasesMaxEntry = MapUtils.getMaxEntry(dailyNewCasesMap);
        Map<String, Integer> dailyNewDeathsMap = getDaysAndValuesBasedOnData(getAllDailyDeathsData(countryName));
        Map.Entry<String, Integer> dailyNewDeathsMinEntry = MapUtils.getMinEntry(dailyNewDeathsMap);
        Map.Entry<String, Integer> dailyNewDeathsMaxEntry = MapUtils.getMaxEntry(dailyNewDeathsMap);
        Map<String, Integer> activeCasesMap = getDaysAndValuesBasedOnData(getAllActiveCasesData(countryName));
        Map.Entry<String, Integer> activeCasesMinEntry = MapUtils.getMinEntry(activeCasesMap);
        Map.Entry<String, Integer> activeCasesMaxEntry = MapUtils.getMaxEntry(activeCasesMap);

        System.out.println(countryName + "'s BEST AND WORST SITUATIONS SINCE CORONA VIRUS STARTED");
        System.out.println("THE HIGHEST NEW CASES NUMBER WAS ON " + dailyNewCasesMaxEntry.getKey() + " WITH " + dailyNewCasesMaxEntry.getValue()
                + " CASES, WITH AN AVERAGE OF " + ArraysUtils.getRecordsAverage(getAllDailyNewCasesData(countryName))
                + " CASES SINCE THEN AND THEY STARTED ON "
                + dailyNewCasesMinEntry.getKey() + "\n");

        System.out.println("THE HIGHEST NEW DEATHS NUMBER WAS ON " + dailyNewDeathsMaxEntry.getKey() + " WITH " + dailyNewDeathsMaxEntry.getValue()
                + " CASES, WITH AN AVERAGE OF " + ArraysUtils.getRecordsAverage(getAllDailyDeathsData(countryName)) + " CASES SINCE THEN AND THEY STARTED  ON "
                + dailyNewDeathsMinEntry.getKey() + "\n");

        System.out.println("THE HIGHEST ACTIVE CASES NUMBER WAS ON " + activeCasesMaxEntry.getKey() + " WITH " + activeCasesMaxEntry.getValue()
                + " CASES, WITH AN AVERAGE OF " + ArraysUtils.getRecordsAverage(getAllActiveCasesData(countryName)) + " CASES SINCE THEN AND THEY STARTED ON "
                + activeCasesMinEntry.getKey());
    }
}
