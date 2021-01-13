package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.Test01GeneralStatistics;
import com.tests.Test02SpecificCuntryStatistics;
import com.tests.Test03Comparisons;
import com.tests.Test04CountryPageDataAnalysis;
import com.tests.Test05PrintCountryTopBestAndWorstSinceStart;

@RunWith(Suite.class)
@SuiteClasses({
    Test01GeneralStatistics.class,
    Test02SpecificCuntryStatistics.class,
    Test03Comparisons.class,
    Test04CountryPageDataAnalysis.class,
    Test05PrintCountryTopBestAndWorstSinceStart.class,
})
public class TestsSuite {
    /**
     * @author vladmarc
     *
     */
}
