package org.mulesoft.salesforce.ui.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.mulesoft.salesforce.ui.automation.page.objects.LoginPage;
import org.mulesoft.salesforce.ui.driver.Browser;
import org.mulesoft.salesforce.ui.page.objects.ForecastPage;
import org.mulesoft.salesforce.ui.page.objects.HomePage;
import org.mulesoft.salesforce.ui.page.objects.VerificationPage;
import org.mulesoft.salesforce.ui.page.objects.workbench.WorkbenchBulkApiJobStatus;
import org.mulesoft.salesforce.ui.page.objects.workbench.WorkbenchHomePage;
import org.mulesoft.salesforce.ui.page.objects.workbench.WorkbenchSoqlQueryPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

import java.io.IOException;

import static org.mulesoft.salesforce.ui.util.TestResult.PASS;
import static org.mulesoft.salesforce.ui.util.WebUtil.takeScreenshot;

@Slf4j
public class StepDefinitions {

    private WebDriver browser;
    private LoginPage loginPage;
    private VerificationPage verificationPage;
    private HomePage homePage;
    private WorkbenchHomePage workbenchHomePage;
    private WorkbenchSoqlQueryPage workbenchSoqlQueryPage;
    private WorkbenchBulkApiJobStatus workbenchBulkApiJobStatus;
    private ForecastPage forecastPage;

    @Given("open browser with url {string}")
    public void openBrowserWithUrl(String url) throws IOException {
        browser = Browser.getWebBrowser();
        browser.get(url);
        browser.manage().window().maximize();
        loginPage = new LoginPage(browser);
    }

    @And("login with {string} and {string}")
    public void loginToSaleForce(String uName, String pswd) throws IOException, InterruptedException {
        loginPage.enterUserName(uName);
        loginPage.enterPassword(pswd);
        takeScreenshot(browser, PASS, "login_page");
        loginPage.clickLogin();
        verificationPage = new VerificationPage(browser);
        takeScreenshot(browser, PASS, "verification_page");
        verificationPage.clickVerify();
            /*homePage = new HomePage(browser);
            homePage.clickForecasts();
            Thread.sleep(60000);*/
    }

    @And("navigate to forecast page with id {string} {string}")
    public void navigateToForecastPage(String url, String forecastId) throws IOException, InterruptedException {

        browser.get(url + forecastId);
        forecastPage = new ForecastPage(browser);
        forecastPage.clickExportButton();

    }

    @And("login to workbench with url {string}")
    public void loginToWorkbench(String url) throws IOException, InterruptedException {
        browser.switchTo().newWindow(WindowType.TAB);
        browser.get(url);
        takeScreenshot(browser, PASS, "workbench_home_page");
    }

    @And("execute query {string} in {string} environment")
    public void executeQuery(String query, String envSelection) throws IOException, InterruptedException {
        workbenchHomePage = new WorkbenchHomePage(browser);
        workbenchHomePage.selectEnvironment(envSelection);
        workbenchHomePage.checkTermsAndConditions();
        takeScreenshot(browser, PASS, "workbench_home_page");
        workbenchHomePage.clickLoginWithSalesforceButton();
        workbenchSoqlQueryPage = new WorkbenchSoqlQueryPage(browser);
        workbenchSoqlQueryPage.clickExportCsvRadioButton();
        workbenchSoqlQueryPage.enterQueryInTextarea(query);
        takeScreenshot(browser, PASS, "workbench_query_page");
        workbenchSoqlQueryPage.clickQueryButton();
    }

    @And("download query result")
    public void downloadQueryResult() throws IOException, InterruptedException {
        workbenchBulkApiJobStatus = new WorkbenchBulkApiJobStatus(browser);
        takeScreenshot(browser, PASS, "workbench_query_result_page");
        workbenchBulkApiJobStatus.downloadQueryReport();
    }

    @Then("quit driver")
    public void quitDriver() {
        browser.quit();
    }
}
