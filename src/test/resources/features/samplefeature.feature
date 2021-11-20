Feature: Connect to FTP to read excel file and do bulk insertions into Salesforce object

  # Mulesoft - salesforce integration - Use case 1
  @SmokeApiTest
  Scenario: Connect to FTP to read excel file
    Given read excel file data
    And read csv file data