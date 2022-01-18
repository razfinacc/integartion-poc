package org.mulesoft.salesforce.ui.util;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.mulesoft.salesforce.ui.driver.Browser;
import org.mulesoft.salesforce.utilities.Util;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;

public class WebUtil {
    private static int screencastCounter = 0;

    public static boolean enterTextIntoTextBox(WebElement textBox, String textToBeEntered) {
        try {
            textBox.sendKeys(textToBeEntered);
        } catch (Exception e) {
            takeScreenshot(Browser.getWebDriver(), TestResult.EXCEPTION, "failed_screencast_" + screencastCounter);
            return false;
        }
        return true;
    }

    public static boolean clickButton(WebElement clickable)  {
        try {
            clickable.click();
        } catch (Exception e) {
            takeScreenshot(Browser.getWebDriver(), TestResult.EXCEPTION, "failed_screencast_" + screencastCounter);
            return false;
        }
        return true;

    }

    public static boolean selectOptionFromSelectBox(WebElement selectBox, String selection) {
        try {
            Select select = new Select(selectBox);
            select.selectByVisibleText(selection);
        } catch (Exception e) {
            takeScreenshot(Browser.getWebDriver(), TestResult.EXCEPTION, "failed_screencast_" + screencastCounter);
            return false;
        }
        return true;
    }

    public static void waitInSeconds(int seconds) throws InterruptedException {
        Thread.sleep(seconds);
    }

    public static void takeScreenshot(WebDriver driver, TestResult result, String screencastName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String path = Util.properties.getProperty("screenshots_path");
            FileUtils.copyFileToDirectory(src, new File(path + screencastName + "_" + screencastCounter));
            screencastCounter++;
        } catch (Exception e) {

        }

    }

    public static void assertTrue(boolean result, String message) {
        Assert.assertTrue(message, result);
    }

    public static void assertFalse(boolean result, String message) {
        Assert.assertTrue(message, result);
        FileUtils.copyFileToDirectory(src, filePath);
    }
}
