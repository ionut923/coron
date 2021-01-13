package com.tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.https.CountryHttpsSteps;

@RunWith(SerenityRunner.class)
public class Test05PrintCountryTopBestAndWorstSinceStart extends BaseTest {
    @Steps
    private CountryHttpsSteps countryHttpsSteps;

    @Test
    public void test05PrintCountryTopBestAndWorstSinceStart() {
        countryHttpsSteps.printCountrySinceStartBestAndWorstDaysInforrmation("Romania");
    }
}
