package org.mulesoft.salesforce.ui.util;

import lombok.Getter;

public enum TestResult {

    PASS("pass"),
    FAIL("fail"),
    EXCEPTION("exception");

    @Getter
    private String result;

    TestResult(String result) {
        this.result = result;
    }
}
