package org.mulesoft.salesforce.ui.page.objects;

import org.mulesoft.salesforce.ui.util.WebUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ForecastPage {

    private WebDriver driver;

    @FindBy(how = How.XPATH, using = "//button[@title='Export']")
    private WebElement exportButton;

    public ForecastPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickExportButton() {
        boolean result = WebUtil.clickButton(exportButton);
        WebUtil.assertTrue(result, "Unable to click export button");
    }
}
