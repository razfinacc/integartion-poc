package org.mulesoft.salesforce.ui.util;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;

public class WebUtil {

    public static void enterTextIntoTextBox(WebElement textBox, String textToBeEntered) {
        textBox.sendKeys(textToBeEntered);
    }

    public static void clickButton(WebElement clickable) {
        clickable.click();
    }

    public static void selectOptionFromSelectBox(WebElement selectBox, String selection){
        Select select = new Select(selectBox);
        select.selectByVisibleText(selection);
    }

    public static void waitInSeconds(int seconds) throws InterruptedException {
        Thread.sleep(seconds);
    }

    public static void takeScreenshot(WebDriver driver, TestResult result, String screencastName) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        switch (result){
            case PASS:
                FileHandler.copy(src, new File("src/target/screenshots/pass" + screencastName));
                break;
            case FAIL:
                FileHandler.copy(src, new File("src/target/screenshots/fail" + screencastName));
                break;
            case EXCEPTION:
                FileHandler.copy(src, new File("src/target/screenshots/exception" + screencastName));
                break;
        }
    }
}
