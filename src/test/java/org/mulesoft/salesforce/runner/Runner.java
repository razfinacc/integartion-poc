package org.mulesoft.salesforce.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/cucumber.html", "junit:target/junit", "json:target/json.json"},
        tags = "@SmokeApiTest",
        features = {"classpath:features"},
        glue = {"org.mulesoft.salesforce.stepdefinitions"}
)
public class Runner {
}