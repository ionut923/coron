package com.tools.constants;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Constants {
    public static final String BASE_URL = "https://www.worldometers.info/coronavirus/";
    public static final String WEB_DRIVERS_PATH = "src" + File.separator + "test" + File.separator + "resources"
            + File.separator + "drivers" + File.separator;
    public static final String TEST_DATA_FILE_PATH = System.getProperty("user.dir") + File.separator + "src"
            + File.separator + "test" + File.separator + "resources" + File.separator + "testdata" + File.separator;
    public static final String DOWNLOAD_FILE_PATH_FOR_ZIP = TEST_DATA_FILE_PATH + "zip"
            + File.separator;
    public static final String TEST_DATA_FILE_PATH_FOR_CSV = "src/test/resources/testdata/csv/";
    public static final String RECORD_NAME_WORLD = "World";
    //tabs options hrefs
    public static final String TAB_NOW = "today";
    public static final String TAB_YESTERDAY = "yesterday";
    public static final List<String> COUNTRY_ALL_PERCENTAGE_VALUES_FIELDS_LIST = Arrays.asList(new String[] { "overallDeathRate",
            "newDeathsToActiveCasesPercentage", "newDeathsToActiveCriticalCasesPercentage", "percentageIncreaseOfTotalCases", "recoveryPercentage",
            "criticalActiveCasesPercentage", "newRecoveredCasesPercentage" });
    public static final List<String> COUNTRY_ALL_ABSOLUTE_VALUES_FIELDS_LIST = Arrays.asList(new String[] { "totalCases",
            "newCases", "totalDeaths", "newDeaths", "totalRecovered",
            "newRecovered", "activeCases", "critical" });

}