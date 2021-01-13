package com.tools.customDrivers;

import com.tools.constants.Constants;

import net.thucydides.core.webdriver.DriverSource;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;

public class CustomChromeDriver implements DriverSource {

    @Override
    public WebDriver newDriver() {
        return setCustomChrome();
    }

    @Override
    public boolean takesScreenshots() {
        return true;
    }

    private WebDriver setCustomChrome() {
        System.out.println("Custom chrome driver instance is created now...");
        System.setProperty("webdriver.chrome.driver", Constants.WEB_DRIVERS_PATH + "chromedriver.exe");
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("download.default_directory", Constants.DOWNLOAD_FILE_PATH_FOR_ZIP);
        chromePrefs.put("profile.default_content_settings.popups", 2);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("start-maximized");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-web-security");
        options.addArguments("--disable-blink-features=BlockCredentialedSubresources");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");

        return new ChromeDriver(options);
    }
}
