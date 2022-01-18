package org.mulesoft.salesforce.ui.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Browser {

    private static WebDriver browser;

    public static WebDriver getWebDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        browser = new ChromeDriver();
        return browser;
    }

    public static void quitDriver() {
        if (null != browser) {
            browser.quit();
        }

    }

}
