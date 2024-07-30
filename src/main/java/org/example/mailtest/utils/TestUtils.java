package org.example.mailtest.utils;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;

@Slf4j
public class TestUtils {

    public static void step(String message) {
        log.info(message);
        Allure.step(message);
    }

    @Attachment(value = "{name}", type = "image/png")
    public static byte[] takeScreenshot(String name) {
        return Selenide.screenshot(OutputType.BYTES);
    }

}
