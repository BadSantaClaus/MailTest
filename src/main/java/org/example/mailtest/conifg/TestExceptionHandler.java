package org.example.mailtest.conifg;

import lombok.extern.slf4j.Slf4j;
import org.example.mailtest.utils.TestUtils;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

@Slf4j
public class TestExceptionHandler implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(ExtensionContext extensionContext, Throwable throwable) throws Throwable {
        try {
            TestUtils.takeScreenshot("Fail screenshot");
        } catch (Exception | Error ignored) {
        }
        throw throwable;
    }
}
