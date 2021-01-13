package com.tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.https.HomeHttpSteps;
import com.tools.constants.Constants;

@RunWith(SerenityRunner.class)
public class Test02SpecificCuntryStatistics extends BaseTest {
    @Steps
    private HomeHttpSteps homeHttpSteps;

    @Test
    public void test02SpecificCuntryStatistics() {
        homeHttpSteps.printSpecificCountryStatistics(Constants.TAB_YESTERDAY, "Romania");
    }
}