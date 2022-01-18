package org.mulesoft.salesforce.utilities;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class ReportUtil {

    private static final String LOG_INFO = "[LOGGER OUTPUT] - ";

    public static void addTestStepLog(String logMessage) {
        log.info(logMessage);
        ExtentCucumberAdapter.addTestStepLog(LOG_INFO + logMessage);
    }

    public static void addScreencastLog(String imagePath, String title) throws IOException {
        ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(imagePath, title);
    }
}
