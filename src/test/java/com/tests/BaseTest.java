package com.tests;

import net.thucydides.core.annotations.Managed;

import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class BaseTest {

    @Managed(uniqueSession = true)
    WebDriver webdriver;

    @Before
    public void setup() {
        //        System.setProperty("http.proxyHost", "localhost");
        //        System.setProperty("http.proxyPort", "9090");
        //        System.setProperty("https.proxyHost", "localhost");
        //        System.setProperty("https.proxyPort", "9090");

    }
}
