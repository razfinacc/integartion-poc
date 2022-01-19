package org.mulesoft.salesforce.ui.page.objects.workbench;

import org.mulesoft.salesforce.ui.util.WebUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class WorkbenchBulkApiJobStatus extends WorkbenchMenuBar {

    private WebDriver driver;

    @FindBy(how = How.XPATH, using = "//img[@src='static/images/downloadIconCompleted.gif?v=54.0.0']")
    private WebElement downloadImage;

    public WorkbenchBulkApiJobStatus(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void downloadQueryReport(){
        boolean result = WebUtil.clickButton(downloadImage);
        WebUtil.assertTrue(result, "Unable to download file");
    }

    public void navigateToSoqlQueryPage(){
        super.navigateToSoqlQuery();
    }
}
