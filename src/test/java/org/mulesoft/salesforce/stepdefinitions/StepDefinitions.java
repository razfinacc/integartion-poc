package org.mulesoft.salesforce.stepdefinitions;

import io.cucumber.java.en.Given;
import org.mulesoft.salesforce.model.SheetData;
import org.mulesoft.salesforce.utilities.ExcelUtility;

import java.util.List;

public class StepDefinitions {

    @Given("get connected to FTP site")
    public void connectToFtpSite(){
        ExcelUtility.getWorkbookHandle("src\\test\\resources\\inputdata.xlsx");
        ExcelUtility.getSheetHandle("Data");
        List<SheetData> data = ExcelUtility.getSheetData();
        data.stream().forEach(System.out::println);
    }
}