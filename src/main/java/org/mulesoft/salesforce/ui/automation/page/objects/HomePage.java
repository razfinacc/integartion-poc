package org.mulesoft.salesforce.ui.automation.page.objects;

import org.mulesoft.salesforce.ui.automation.WebUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private WebDriver driver;

    @FindBy(how = How.XPATH, using = "//span[@text()='Forecasts']")
    private WebElement forecastSpan;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickForecasts() {
        WebUtil.clickButton(forecastSpan);
    }
}
