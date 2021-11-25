package org.mulesoft.salesforce.stepdefinitions;

import io.cucumber.java.Before;
import org.mulesoft.salesforce.utilities.Util;

public class Hooks {

    @Before
    public void loadConfiguration(){
        Util.loadProperties();
    }
}
