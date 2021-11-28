Feature: Connect to FTP to read excel file and do bulk insertions into Salesforce object

  # Mulesoft - salesforce integration - Use case 1
  @SmokeApiTest
  Scenario: Connect to FTP to read excel file
    Given read excel file data

  @SmokeApiTest
  Scenario: Connect to FTP to read csv file
    Given read csv file data

  @SmokeApiTest
  Scenario: Platform event status
    Given print CP_Forecast__c.Mulesoft_Event_Received__c
    And print CP_Forecast__c.Mulesoft_Processed_On__c
    And print CP_Forecast__c.Mulesoft_Processed_Success__c