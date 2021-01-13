package com.pages;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import net.serenitybdd.core.pages.PageObject;

public class AbstractPage extends PageObject {
    public void clickOnElement(WebElement elem) {
        JavascriptExecutor js = (JavascriptExecutor)getDriver();
        js.executeScript("arguments[0].click();", elem);
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor)getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void navigateToUrl(String url) {
        getDriver().navigate().to(url);
    }
}
