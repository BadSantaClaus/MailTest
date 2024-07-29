package org.example.mailtest;

import io.qameta.allure.Epic;
import org.example.mailtest.conifg.TestExceptionHandler;
import org.example.mailtest.steps.MailRuSteps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@Epic("Mail.ru")
@DisplayName("Mail.ru")
@SpringBootTest
@ExtendWith(TestExceptionHandler.class)
class MailRuTests {

    @Autowired
    private MailRuSteps mailRuSteps;

    @Value("${creds.username}")
    private String commonUsername;
    @Value("${creds.password}")
    private String commonPassword;

    @Test
    @DisplayName("Проверка отправки сообщения самому себе")
    public void selfMessageTest() {
        String message = "test";

        mailRuSteps
                .authorize(commonUsername, commonPassword)
                .sendMessage(commonUsername, message);
    }

}
