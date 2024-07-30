package org.example.mailtest.conifg;

import com.codeborne.selenide.Configuration;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

@org.springframework.context.annotation.Configuration
public class SelenideConfiguration {

    @Value("${service.mail_ru.host}")
    private String baseUrl;

    @PostConstruct
    public void setup() {
        Configuration.baseUrl = baseUrl;
        Configuration.browser = "chrome";
        Configuration.browserVersion = "123";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = false;
        Configuration.timeout = 60 * 1000;
    }
}
