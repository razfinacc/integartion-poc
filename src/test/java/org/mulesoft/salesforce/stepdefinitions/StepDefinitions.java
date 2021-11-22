package org.mulesoft.salesforce.stepdefinitions;

import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.mulesoft.salesforce.model.SheetData;
import org.mulesoft.salesforce.utilities.CsvUtility;
import org.mulesoft.salesforce.utilities.ExcelUtility;
import org.mulesoft.salesforce.utilities.Utility;

import java.util.List;

@Slf4j
public class StepDefinitions {

    @Given("read excel file data")
    public void readExcelFileData() {
        String excelFilePath = Utility.properties.getProperty("excel_file_path");
        ExcelUtility.getWorkbookHandle(excelFilePath);
        ExcelUtility.getSheetHandle("Data");
        List<SheetData> data = ExcelUtility.getSheetData();
        log.info("Total records read from excel file: {}", data.size());
        System.out.println("Excel Data *****************");
        data.stream().forEach(System.out::println);
    }

    @Given("read csv file data")
    public void readCsvFileData() {
        String csvFilePath = Utility.properties.getProperty("csv_file_path");
        CsvUtility.getCsvHandle(csvFilePath);
        List<SheetData> data = CsvUtility.getCsvData();
        log.info("Total records read from csv file: {}", data.size());
        System.out.println("CSV Data *****************");
        data.stream().forEach(System.out::println);
        Assert.assertEquals(1,2);
    }
}