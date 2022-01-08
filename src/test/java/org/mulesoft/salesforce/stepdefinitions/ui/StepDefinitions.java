package org.mulesoft.salesforce.stepdefinitions.ui;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.mulesoft.salesforce.ui.automation.driver.Driver;
import org.mulesoft.salesforce.ui.automation.page.objects.LoginPage;
import org.openqa.selenium.WebDriver;

@Slf4j
public class StepDefinitions {

    private WebDriver browser;
    private LoginPage loginPage;

    @Given("open browser with url {string}")
    public void openBrowserWithUrl(String url) {
        browser = Driver.getWebDriver();
        browser.get(url);
        browser.manage().window().maximize();
        loginPage = new LoginPage(browser);
    }

    @Given("login with {string} and {string}")
    public void enterUserName(String uName, String pswd) throws InterruptedException {
        loginPage.enterUserName(uName);
        Thread.sleep(2000);
        loginPage.enterPassword(pswd);
        Thread.sleep(2000);
        loginPage.clickLogin();
        Thread.sleep(10000);
    }

    @Then("quit driver")
    public void quitDriver() {
        browser.quit();
    }
}
