package org.mulesoft.salesforce;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/cucumber"},
        features = "src/test/resources/features",
        glue = {"src/test/java/org/mulesoft/salesforce/StepDefinitions"},
        tags = "@smokeApiTest",
        publish = true
)
public class Runner {


}
