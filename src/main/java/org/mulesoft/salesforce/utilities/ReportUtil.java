package org.mulesoft.salesforce.utilities;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReportUtil {

    private static final String LOG_INFO = "[LOGGER OUTPUT] - ";

    public static void addTestStepLog(String logMessage) {
        log.info(logMessage);
        ExtentCucumberAdapter.addTestStepLog(LOG_INFO + logMessage);
    }
}
