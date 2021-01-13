package com.tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.https.HomeHttpSteps;
import com.tools.constants.Constants;

@RunWith(SerenityRunner.class)
public class Test03Comparisons extends BaseTest {
    @Steps
    private HomeHttpSteps homeHttpSteps;

    @Test
    public void test03Comparisons() {
        homeHttpSteps.printCountryUpdatesSinceYesterday("Romania");
        homeHttpSteps.printSpecificCountriesStatisticsComparisons(Constants.TAB_YESTERDAY, "Romania", Constants.RECORD_NAME_WORLD);
        homeHttpSteps.printSpecificCountryComparisonToBestAndWorstTopCountries(Constants.TAB_YESTERDAY, "Romania");
    }
}