package org.mulesoft.salesforce.ui.page.objects;

import org.mulesoft.salesforce.ui.util.WebUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class VerificationPage {

    private WebDriver driver;

    @FindBy(how = How.ID, using = "smc")
    private WebElement verificationText;

    @FindBy(how = How.ID, using = "save")
    private WebElement verifyButton;


    public VerificationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickVerify() {
        boolean result = WebUtil.clickButton(verifyButton);
        WebUtil.assertTrue(result, "Unable to click verify button");

    }
}
