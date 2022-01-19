package org.mulesoft.salesforce.ui.util;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.mulesoft.salesforce.ui.driver.Browser;
import org.mulesoft.salesforce.utilities.ReportUtil;
import org.mulesoft.salesforce.utilities.Util;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Comparator;
import java.util.Optional;

public class WebUtil {

    private static int waitForSeconds = 60;
    private static int screencastCounter = 0;

    public static void waitForElementToBeLoaded(WebElement webElement) {
        WebDriverWait webDriverWait = new WebDriverWait(Browser.getWebBrowser(), Duration.ofSeconds(waitForSeconds));
        webDriverWait.until(ExpectedConditions.or(
                ExpectedConditions.elementToBeClickable(webElement),
                ExpectedConditions.elementToBeSelected(webElement)
        ));
    }

    public static boolean enterTextIntoTextBox(WebElement textBox, String textToBeEntered) {
        try {
            waitForElementToBeLoaded(textBox);
            textBox.sendKeys(textToBeEntered);
        } catch (Exception e) {
            takeScreenshot(Browser.getWebBrowser(), TestResult.EXCEPTION, "failed_screencast");
            return false;
        }
        return true;
    }

    public static boolean clickButton(WebElement clickable) {
        try {
            waitForElementToBeLoaded(clickable);
            clickable.click();
        } catch (Exception e) {
            takeScreenshot(Browser.getWebBrowser(), TestResult.EXCEPTION, "failed_screencast");
            return false;
        }
        return true;

    }

    public static boolean selectOptionFromSelectBox(WebElement selectBox, String selection) {
        try {
            waitForElementToBeLoaded(selectBox);
            Select select = new Select(selectBox);
            select.selectByVisibleText(selection);
        } catch (Exception e) {
            takeScreenshot(Browser.getWebBrowser(), TestResult.EXCEPTION, "failed_screencast");
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
            String path = Util.properties.getProperty("screenshots_path") + screencastName + "_" + screencastCounter + ".png";
            FileUtils.copyFile(src, new File(path));
            screencastCounter++;
            ReportUtil.addScreencastLog(path, result.name());
        } catch (Exception e) {

        }
    }

    public static void assertTrue(boolean result, String message) {
        Assert.assertTrue(message, result);
    }

    public static void assertFalse(boolean result, String message) {
        Assert.assertTrue(message, result);
    }

    public static void copyFile(Path src, Path dest) throws IOException {
        Optional<Path> lastFilePath = Files.list(src)
                .filter(it -> !Files.isDirectory(it))
                .filter(it -> it.getFileName().toString().contains("ForecastingGridData"))
                .max(Comparator.comparingLong(it -> it.toFile().lastModified()));
        if (lastFilePath.isPresent()) {
            FileUtils.copyFile(lastFilePath.get().toFile(), new File(String.valueOf(dest)));
        }
    }
}
