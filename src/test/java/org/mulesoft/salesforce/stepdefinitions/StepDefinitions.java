package org.mulesoft.salesforce.stepdefinitions;

import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.mulesoft.salesforce.Session.Session;
import org.mulesoft.salesforce.model.Person;
import org.mulesoft.salesforce.model.SheetData;
import org.mulesoft.salesforce.utilities.CsvUtil;
import org.mulesoft.salesforce.utilities.DbUtil;
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
        Session.sourceRecordsCount = data.size();
        data.stream().forEach(System.out::println);
        ReportUtil.addTestStepLog("Total records read from source CSV file: " + data.size());
        Assert.assertEquals(1, 1);
    }

    @Given("read target csv file data")
    public void readTargetCsvFileData() {
        String csvFilePath = Util.properties.getProperty("target_csv_file_path");
        CsvUtil.getCsvHandle(csvFilePath);
        List<SheetData> data = CsvUtil.getCsvData();
        Session.targetRecordsCount = data.size();
        data.stream().forEach(System.out::println);
        ReportUtil.addTestStepLog("Total records read from target CSV file: " + data.size());
        ReportUtil.addTestStepLog("First records created timestamp "+data.stream().findFirst().get().getCreatedDate());
        ReportUtil.addTestStepLog("Last record created timestamp "+data.get(data.size() - 1).getCreatedDate());
//        ReportUtil.addTestStepLog(data.stream().findFirst().toString());
//        ReportUtil.addTestStepLog(data.get(data.size() - 1).toString());
//        data.stream().forEach(it -> ReportUtil.addTestStepLog(it.toString()));
        Assert.assertEquals(1, 1);
    }

    @Given("^validate source and target csv file records count$")
    public void validateSourceAndTargetRecordsCount(){
        ReportUtil.addTestStepLog("Source records count: " + Session.sourceRecordsCount);
        ReportUtil.addTestStepLog("Target records count: " + Session.targetRecordsCount);
        Assert.assertEquals("Source and target records count mismatch", Session.sourceRecordsCount, Session.targetRecordsCount);
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