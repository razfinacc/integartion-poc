package org.mulesoft.salesforce.ui.automation.page.objects;

import org.mulesoft.salesforce.ui.util.WebUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;

    @FindBy(how = How.ID, using = "username")
    private WebElement userName;

    @FindBy(how = How.ID, using = "password")
    private WebElement password;

    @FindBy(how = How.ID, using = "Login")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUserName(String uName) {
        WebUtil.enterTextIntoTextBox(userName, uName);
    }

    public void enterPassword(String pswd) {
        WebUtil.enterTextIntoTextBox(password, pswd);
    }

    public void clickLogin() {
        WebUtil.clickButton(loginButton);
    }
}
