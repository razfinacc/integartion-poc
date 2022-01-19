Feature: Connect to FTP to read excel file and do bulk insertions into Salesforce object

  @BrowserAutomation @SmokeApiTest
  Scenario Outline: Browser automation
    Given open browser with url "<url>"
    And login with "<uName>" and "<pswd>"
    And navigate to forecast page with id "<url>" "<forecastId>"
    And login to workbench with url "https://workbench.developerforce.com/query.php"
    And navigate to workbench query page "<envSelection>"
    And execute query "<query1>" in "<envSelection>" environment
    And download query result
    And open workbench soql query page from menu bar
    And execute query "<query2>" in "<envSelection>" environment
    Then quit driver
    Examples:
      | url                                     | uName | pswd | forecastId         | envSelection | query1                                                                                                                                                                                                                                                                  | query2 |
      | https://wb--cptechup.my.salesforce.com/ | xxx   | yyy  | a7802000000995gAAA | Sandbox      | SELECT CP_Contract__c, Forecast_Currency__c , CP_Property_Code__c, CP_Product_Type__c, Retailer__c, Revenue_Type__c, Royalty_Rate__c, Q1__c,Q2__c,Q3__c,Q4__c , Total_Amount__c, CreatedDate, LastModifiedDate FROM CP_Forecast_Item__c WHERE  id= 'a7802000000995gAAA' |        |

  @Csv @SmokeApiTest
  Scenario: Connect to Mulesoft to read source csv file
    Given read source csv file data

  @Csv @SmokeApiTest
  Scenario: Connect to Salesforce to read target csv file
    Given read target csv file data

  @Csv @SmokeApiTest
  Scenario: Connect to Salesforce to validate source and target data records count
    Given validate source and target csv file records count

  @SmokeApiTest
  Scenario: Platform event status
    Given print CP_Forecast__c.Mulesoft_Event_Received__c
    And print CP_Forecast__c.Mulesoft_Processed_On__c
    And print CP_Forecast__c.Mulesoft_Processed_Success__c

  @Database@SmokeApiTest
  Scenario: Connect to FTP to read database data
    Given read database data