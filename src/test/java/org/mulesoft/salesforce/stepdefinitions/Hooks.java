package org.mulesoft.salesforce.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.mulesoft.salesforce.ui.driver.Browser;
import org.mulesoft.salesforce.utilities.Util;

import java.io.File;

public class Hooks {

    @Before
    public void loadConfiguration() {
        Util.loadProperties();
    }

    @Before
    public void createScreenshotsDirectory() {
        Util.createDirectoryIfNotExists(new File(Util.properties.getProperty("screenshots_path") + "pass"));
        Util.createDirectoryIfNotExists(new File(Util.properties.getProperty("screenshots_path") + "fail"));
        Util.createDirectoryIfNotExists(new File(Util.properties.getProperty("screenshots_path") + "exception"));
    }

    @After
    public void quitDriver() {
        Browser.quitDriver();
    }
}

