package org.mulesoft.salesforce.ui.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Browser {

    private static WebDriver browser;
    private static final String CHROME_DRIVER = "webdriver.chrome.driver";
    private static final String DRIVER_PATH = "src/test/resources/drivers/chromedriver.exe";

    public static WebDriver createWebBrowser() {
        System.setProperty(CHROME_DRIVER, DRIVER_PATH);
        browser = new ChromeDriver();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        browser.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        return browser;
    }

    public static WebDriver getWebBrowser() {
        if (null != browser) {
            return browser;
        }
        return createWebBrowser();
    }

    public static void quitDriver() {
        if (null != browser) {
            browser.quit();
        }
    }

}
