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
        boolean result = WebUtil.clickButton(exportCsvRadioButton);
        WebUtil.assertTrue(result, "Unable to click bulk csv radio button ");
    }

    public void enterQueryInTextarea(String query) {
        boolean result = WebUtil.enterTextIntoTextBox(soqlQueryTextarea, query);
        WebUtil.assertTrue(result, "Unable to enter query in text area ");
    }

    public void clickQueryButton() {
        boolean result = WebUtil.clickButton(queryButton);
        WebUtil.assertTrue(result, "Unable to click query button ");
    }
}
