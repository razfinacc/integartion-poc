# integartion-poc

Integration Mule POC

Tech Stack:
> Java 8
 
> Selenium 4
 
> Cucumber 7
 
> Extentreports-cucumber7-adapter 1.1
 
> Junit 5.4
 
> slf4j-jdk14 1.7
 
> Rest assured 4.3
 
> Apache poi 5
 
> Hibernate-core 5.6

> Lombok 1.16

src/main/java
> model:      holds all model classes

> session:    putting global variables in session

> ui:         holds all ui automation stuff
>>        page.objects:   holds all pages
>>        util:   holds all selenium actions

> utilities:  holds all utility actions
>>       CsvUtil:    reading and writing to csv file
>>       DbUtil:     connecting and accessing database
>>       ExcelUtil:  reading and writing to excel file
>>       ReportUtil: generates and updates automation report file
>>       Util:       reads data from properties file

src/main/resources

src/java/test
> runner:             tests initiator

> stepdefinitions:    cucumber feature to java connection
>> api:    api related mappings
> > ui:     ui related mappings Hooks:              all hooks are captured here

src/test/resources
> drivers:    all drivers required to run ui automation features:   complete cucumber feature files

> .gitignore:     holds information regarding un-versioned files

> pom.xml:        build file

> readme.md:      preface