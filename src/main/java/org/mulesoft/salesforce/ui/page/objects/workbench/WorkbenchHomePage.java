package org.mulesoft.salesforce.ui.page.objects.workbench;

import org.mulesoft.salesforce.ui.util.WebUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class WorkbenchHomePage {

    private WebDriver driver;

    @FindBy(how = How.ID, using = "oauth_env")
    private WebElement environmentSelectBox;

    @FindBy(how = How.ID, using = "termsAccepted")
    private WebElement termsAndConditionsCheckBox;

    @FindBy(how = How.ID, using = "loginBtn")
    private WebElement loginWithSalesforceButton;

    public WorkbenchHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectEnvironment(String selection) {
        boolean result = WebUtil.selectOptionFromSelectBox(environmentSelectBox, selection);
        WebUtil.assertTrue(result, "Unable to select " + selection + " env");
    }

    public void checkTermsAndConditions() {
        boolean result = WebUtil.clickButton(termsAndConditionsCheckBox);
        WebUtil.assertTrue(result, "Unable to check terms and conditions ");
    }

    public void clickLoginWithSalesforceButton() {
        boolean result = WebUtil.clickButton(loginWithSalesforceButton);
        WebUtil.assertTrue(result, "Unable to click on workbench login button");
    }
}
