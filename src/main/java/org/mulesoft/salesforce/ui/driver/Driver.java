package org.mulesoft.salesforce.ui.automation.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {

    public static WebDriver getWebDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        return new ChromeDriver();
    }

}
