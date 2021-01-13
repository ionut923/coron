package com.tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.https.CountryHttpsSteps;

@RunWith(SerenityRunner.class)
public class Test04CountryPageDataAnalysis extends BaseTest {
    @Steps
    private CountryHttpsSteps countryHttpSteps;

    @Test
    public void test04CountryPageDataAnalysis() {
        countryHttpSteps.printCountryDailyInformation("Romania", 28);
        countryHttpSteps.printCountryBestAndWorstDaysInforrmation("Romania", 28);
        countryHttpSteps.printCountryDailyInformation("Romania", 14);
        countryHttpSteps.printCountryBestAndWorstDaysInforrmation("Romania", 14);

    }
}
