Feature: Connect to FTP to read excel file and do bulk insertions into Salesforce object

  # Mulesoft - salesforce integration - Use case 1
#  @SmokeApiTest
#  Scenario: Connect to Mulesoft to read excel file
#    Given read excel file data

  @SmokeApiTest
  Scenario: Connect to Mulesoft to read source csv file
    Given read source csv file data

  @SmokeApiTest
  Scenario: Connect to Salesforce to read target csv file
    Given read target csv file data

  @SmokeApiTest
  Scenario: Connect to Salesforce to validate source and target data records count
    Given validate source and target csv file records count

  @SmokeApiTest
  Scenario: Platform event status
    Given print CP_Forecast__c.Mulesoft_Event_Received__c
    And print CP_Forecast__c.Mulesoft_Processed_On__c
    And print CP_Forecast__c.Mulesoft_Processed_Success__c

  @SmokeApiTest
  Scenario: Connect to FTP to read database data
    Given read database data