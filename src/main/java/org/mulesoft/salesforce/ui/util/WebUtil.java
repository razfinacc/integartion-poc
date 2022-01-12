package org.mulesoft.salesforce.ui.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

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
}
