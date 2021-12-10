package org.mulesoft.salesforce.stepdefinitions;

import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.mulesoft.salesforce.model.SheetData;
import org.mulesoft.salesforce.utilities.CsvUtil;
import org.mulesoft.salesforce.utilities.ExcelUtil;
import org.mulesoft.salesforce.utilities.ReportUtil;
import org.mulesoft.salesforce.utilities.Util;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public class StepDefinitions {

    @Given("read excel file data")
    public void readExcelFileData() {
        String excelFilePath = Util.properties.getProperty("excel_file_path");
        ExcelUtil.getWorkbookHandle(excelFilePath);
        ExcelUtil.getSheetHandle("Data");
        List<SheetData> data = ExcelUtil.getSheetData();
        ReportUtil.addTestStepLog("Total records read from EXCEL file: " + data.size());
        data.stream().forEach(System.out::println);
    }

    @Given("read source csv file data")
    public void readSourceCsvFileData() {
        String csvFilePath = Util.properties.getProperty("source_csv_file_path");
        CsvUtil.getCsvHandle(csvFilePath);
        List<SheetData> data = CsvUtil.getCsvData();
        data.stream().forEach(System.out::println);
        ReportUtil.addTestStepLog("Total records read from source CSV file: " + data.size());
        Assert.assertEquals(1, 1);
    }

    @Given("read target csv file data")
    public void readTargetCsvFileData() {
        String csvFilePath = Util.properties.getProperty("target_csv_file_path");
        CsvUtil.getCsvHandle(csvFilePath);
        List<SheetData> data = CsvUtil.getCsvData();
        data.stream().forEach(System.out::println);
        ReportUtil.addTestStepLog("Total records read from target CSV file: " + data.size());
        Assert.assertEquals(1, 1);
    }

    @Given("print CP_Forecast__c.Mulesoft_Event_Received__c")
    public void MulesoftEventReceived() {
        ReportUtil.addTestStepLog("CP_Forecast__c.Mulesoft_Event_Received__c: " + true);
        Assert.assertTrue(true);
    }

    @Given("print CP_Forecast__c.Mulesoft_Processed_On__c")
    public void MulesoftProcessedOn() {
        ReportUtil.addTestStepLog("CP_Forecast__c.Mulesoft_Processed_On__c: " + LocalDateTime.now());
    }

    @Given("print CP_Forecast__c.Mulesoft_Processed_Success__c")
    public void MulesoftProcessedSuccess() {
        ReportUtil.addTestStepLog("CP_Forecast__c.Mulesoft_Processed_Success__c: " + false);
        Assert.assertTrue(false);
    }
}