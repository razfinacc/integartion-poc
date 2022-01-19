package org.mulesoft.salesforce.ui.page.objects.workbench;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class WorkbenchMenuBar {

    private WebDriver browser;

    @FindBy(how = How.XPATH, using = "//span[text()='queries]")
    private WebElement queries;

    @FindBy(how = How.XPATH, using = "//a[text()='SOQL Query]")
    private WebElement soqlQuery;

    public WorkbenchMenuBar(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    public void navigateToSoqlQuery() {
        Actions actions = new Actions(browser);
        actions.moveToElement(queries).perform();
        actions.moveToElement(soqlQuery).click().perform();
    }
}
