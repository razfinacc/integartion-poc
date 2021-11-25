package org.mulesoft.salesforce.stepdefinitions;

import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.mulesoft.salesforce.model.Person;
import org.mulesoft.salesforce.model.SheetData;
import org.mulesoft.salesforce.utilities.CsvUtil;
import org.mulesoft.salesforce.utilities.DbUtil;
import org.mulesoft.salesforce.utilities.ExcelUtil;
import org.mulesoft.salesforce.utilities.ReportUtil;
import org.mulesoft.salesforce.utilities.Util;

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

    @Given("read csv file data")
    public void readCsvFileData() {
        String csvFilePath = Util.properties.getProperty("csv_file_path");
        CsvUtil.getCsvHandle(csvFilePath);
        List<SheetData> data = CsvUtil.getCsvData();
        data.stream().forEach(System.out::println);
        ReportUtil.addTestStepLog("Total records read from CSV file: " + data.size());
        Assert.assertEquals(1, 2);
    }

    @Given("read database data")
    public void readDatabaseData() {
        DbUtil.openDbConnection();
        Person person1 = new Person();
        person1.setFirstName("FirstName1");
        person1.setLastName("LastName1");
        person1.setAge(40);
        DbUtil.savePerson(person1);

        Person person2 = new Person();
        person2.setFirstName("FirstName2");
        person2.setLastName("LastName2");
        person2.setAge(50);
        DbUtil.savePerson(person2);

        DbUtil.getPersons();
        DbUtil.getPerson(1);
        DbUtil.removePerson(person1);
        DbUtil.getPersons();
        DbUtil.closeConnection();
    }
}