package org.mulesoft.salesforce.ui.page.objects.workbench;

import org.mulesoft.salesforce.ui.util.WebUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class WorkbenchSoqlQueryPage {

    private WebDriver driver;

    @FindBy(how = How.ID, using = "export_action_async_csv")
    private WebElement exportCsvRadioButton;

    @FindBy(how = How.ID, using = "soql_query_textarea")
    private WebElement soqlQueryTextarea;

    @FindBy(how = How.NAME, using = "querySubmit")
    private WebElement queryButton;

    public WorkbenchSoqlQueryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickExportCsvRadioButton() {
        WebUtil.clickButton(exportCsvRadioButton);
    }

    public void enterQueryInTextarea(String query) {
        WebUtil.enterTextIntoTextBox(soqlQueryTextarea, query);
    }

    public void clickQueryButton() {
        WebUtil.clickButton(queryButton);
    }
}
