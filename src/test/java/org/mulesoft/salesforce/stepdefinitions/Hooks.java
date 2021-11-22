package org.mulesoft.salesforce.stepdefinitions;

import io.cucumber.java.Before;
import org.mulesoft.salesforce.utilities.Utility;

public class Hooks {

    @Before
    public void loadConfiguration(){
        Utility.loadProperties();
    }
}
