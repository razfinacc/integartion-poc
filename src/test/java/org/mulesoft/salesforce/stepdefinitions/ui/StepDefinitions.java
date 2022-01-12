package org.mulesoft.salesforce.stepdefinitions.ui;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.mulesoft.salesforce.ui.automation.driver.Driver;
import org.mulesoft.salesforce.ui.automation.page.objects.LoginPage;
import org.mulesoft.salesforce.ui.page.objects.HomePage;
import org.mulesoft.salesforce.ui.page.objects.VerificationPage;
import org.mulesoft.salesforce.ui.page.objects.workbench.WorkbenchBulkApiJobStatus;
import org.mulesoft.salesforce.ui.page.objects.workbench.WorkbenchHomePage;
import org.mulesoft.salesforce.ui.page.objects.workbench.WorkbenchSoqlQueryPage;
import org.openqa.selenium.WebDriver;

@Slf4j
public class StepDefinitions {

    private WebDriver browser;
    private LoginPage loginPage;
    private VerificationPage verificationPage;
    private HomePage homePage;
    private WorkbenchHomePage workbenchHomePage;
    private WorkbenchSoqlQueryPage workbenchSoqlQueryPage;
    private WorkbenchBulkApiJobStatus workbenchBulkApiJobStatus;

    @Given("open browser with url {string}")
    public void openBrowserWithUrl(String url) {
        browser = Driver.getWebDriver();
        browser.get(url);
        browser.manage().window().maximize();
        loginPage = new LoginPage(browser);
    }

    @And("login with {string} and {string}")
    public void loginToSaleForce(String uName, String pswd) throws InterruptedException {
        loginPage.enterUserName(uName);
        Thread.sleep(2000);
        loginPage.enterPassword(pswd);
        Thread.sleep(2000);
        loginPage.clickLogin();
        Thread.sleep(30000);
        verificationPage = new VerificationPage(browser);
        verificationPage.clickVerify();
        Thread.sleep(10000);
        /*homePage = new HomePage(browser);
        homePage.clickForecasts();
        Thread.sleep(60000);*/
    }

    @And("login to workbench with url {string}")
    public void loginToWorkbench(String url) throws InterruptedException {
        browser.get(url);
        Thread.sleep(10000);
    }

    @And("execute query {string} in {string} environment")
    public void executeQuery(String query, String envSelection) throws InterruptedException {
        workbenchHomePage = new WorkbenchHomePage(browser);
        workbenchHomePage.selectEnvironment(envSelection);
        Thread.sleep(2000);
        workbenchHomePage.checkTermsAndConditions();
        Thread.sleep(2000);
        workbenchHomePage.clickLoginWithSalesforceButton();
        Thread.sleep(10000);
        workbenchSoqlQueryPage = new WorkbenchSoqlQueryPage(browser);
        workbenchSoqlQueryPage.clickExportCsvRadioButton();
        Thread.sleep(2000);
        workbenchSoqlQueryPage.enterQueryInTextarea(query);
        Thread.sleep(2000);
        workbenchSoqlQueryPage.clickQueryButton();
        Thread.sleep(10000);
    }

    @And("download query result")
    public void downloadQueryResult() throws InterruptedException {
        workbenchBulkApiJobStatus = new WorkbenchBulkApiJobStatus(browser);
        workbenchBulkApiJobStatus.downloadQueryReport();
        Thread.sleep(5000);
    }

    @Then("quit driver")
    public void quitDriver() {
        browser.quit();
    }
}
