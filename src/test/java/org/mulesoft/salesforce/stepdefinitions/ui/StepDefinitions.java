package org.mulesoft.salesforce.stepdefinitions.ui;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.mulesoft.salesforce.ui.automation.driver.Driver;
import org.mulesoft.salesforce.ui.automation.page.objects.LoginPage;
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
import static org.mulesoft.salesforce.ui.util.TestResult.EXCEPTION;
import static org.mulesoft.salesforce.ui.util.WebUtil.takeScreenshot;
import static org.mulesoft.salesforce.ui.util.WebUtil.waitInSeconds;

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
        try {
            browser = Driver.getWebDriver();
            browser.get(url);
            browser.manage().window().maximize();
            loginPage = new LoginPage(browser);
        } catch (Exception e) {
            takeScreenshot(browser, EXCEPTION, "open_browser_fail.png");
        }
    }

    @And("login with {string} and {string}")
    public void loginToSaleForce(String uName, String pswd) throws IOException {
        try {
            loginPage.enterUserName(uName);
            waitInSeconds(2000);
            loginPage.enterPassword(pswd);
            waitInSeconds(2000);
            takeScreenshot(browser, PASS, "login_page.png");
            loginPage.clickLogin();
            waitInSeconds(30000);
            verificationPage = new VerificationPage(browser);
            takeScreenshot(browser, PASS, "verification_page.png");
            verificationPage.clickVerify();
            waitInSeconds(10000);
            /*homePage = new HomePage(browser);
            homePage.clickForecasts();
            Thread.sleep(60000);*/
        } catch (Exception e) {
            takeScreenshot(browser, EXCEPTION, "login_to_salesforce_fail.png");
        }
    }

    @And("navigate to forecast page with id {string} {string}")
    public void navigateToForecastPage(String url, String forecastId) throws IOException {
        try {
            browser.get(url + forecastId);
            waitInSeconds(10000);
            forecastPage = new ForecastPage(browser);
            forecastPage.clickExportButton();
            waitInSeconds(10000);

        } catch (Exception e) {
            takeScreenshot(browser, EXCEPTION, "open_browser_fail.png");
        }
    }

    @And("login to workbench with url {string}")
    public void loginToWorkbench(String url) throws IOException {
        try {
            browser.switchTo().newWindow(WindowType.TAB);
            browser.get(url);
            waitInSeconds(10000);
            takeScreenshot(browser, PASS, "workbench_home_page.png");
        } catch (Exception e) {
            takeScreenshot(browser, EXCEPTION, "login_to_workbench_fail.png");
        }
    }

    @And("execute query {string} in {string} environment")
    public void executeQuery(String query, String envSelection) throws IOException {
        try {
            workbenchHomePage = new WorkbenchHomePage(browser);
            workbenchHomePage.selectEnvironment(envSelection);
            waitInSeconds(2000);
            workbenchHomePage.checkTermsAndConditions();
            waitInSeconds(2000);
            takeScreenshot(browser, PASS, "workbench_home_page.png");
            workbenchHomePage.clickLoginWithSalesforceButton();
            waitInSeconds(10000);
            workbenchSoqlQueryPage = new WorkbenchSoqlQueryPage(browser);
            workbenchSoqlQueryPage.clickExportCsvRadioButton();
            waitInSeconds(2000);
            workbenchSoqlQueryPage.enterQueryInTextarea(query);
            waitInSeconds(2000);
            takeScreenshot(browser, PASS, "workbench_query_page.png");
            workbenchSoqlQueryPage.clickQueryButton();
        } catch (Exception e) {
            takeScreenshot(browser, EXCEPTION, "execute_query_fail.png");
        }
    }

    @And("download query result")
    public void downloadQueryResult() throws IOException {
        try {
            workbenchBulkApiJobStatus = new WorkbenchBulkApiJobStatus(browser);
            takeScreenshot(browser, PASS, "workbench_query_result_page.png");
            workbenchBulkApiJobStatus.downloadQueryReport();
            waitInSeconds(5000);
        } catch (Exception e) {
            takeScreenshot(browser, EXCEPTION, "download_query_result_fail.png");
        }

    }

    @Then("quit driver")
    public void quitDriver() {
        browser.quit();
    }
}
