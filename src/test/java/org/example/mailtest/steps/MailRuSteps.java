package org.example.mailtest.steps;

import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;
import org.example.mailtest.page.mail_ru.AuthorizationPage;
import org.example.mailtest.page.mail_ru.MailPage;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailRuSteps {

    private final AuthorizationPage authorizationPage;
    private final MailPage mailPage;

    @Step("Авторизоваться в почте")
    public MailRuSteps authorize(String username, String password) {
        authorizationPage.authorize(username, password);
        return this;
    }

    @Step("Отправить сообщение '{message}' по адресу '{email}'")
    public void sendMessage(String email, String message) {
        authorizationPage.getCreateLetter().click();
        mailPage.sendMessage(email, message);
    }
}
