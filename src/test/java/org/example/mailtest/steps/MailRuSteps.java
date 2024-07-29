package org.example.mailtest.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.example.mailtest.elements.UiButton;
import org.example.mailtest.elements.UiTextBox;
import org.example.mailtest.utils.TestUtils;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

@Component
public class MailRuSteps {

    private final UiButton enter = new UiButton($x("//button[text() = 'Войти']"), "Войти");
    private final UiButton enterLogin = new UiButton($x("//span[text()= 'Войти']"), "Войти");
    private final UiButton createLetter = new UiButton($x("//a[contains(text(), 'Написать письмо')]"), "Написать письмо");
    private final UiButton send = new UiButton($x("//button[@data-test-id = 'send']"), "Отправить");

    private final UiTextBox username = new UiTextBox($x("//input[@placeholder = 'Имя аккаунта']"), "Имя аккаунта");
    private final UiTextBox passsword = new UiTextBox($x("//input[@placeholder = 'Пароль']"), "Пароль");
    private final UiTextBox email = new UiTextBox($x("///div[text() = 'Кому']//following::input"), "Кому");
    private final UiTextBox message = new UiTextBox($x("//div[contains(@class, 'editor_container')]//div[@role='textbox']"), "Сообщение");

    private final SelenideElement checkSending = $x("//span[@data-qa-id='message'");
    private final SelenideElement currentEmail = $x("//h4[@data-test-id='current-email']");

    @Step("Авторизоваться в почте")
    public MailRuSteps authorize(String username, String password) {
        open("");
        enter.click();
        setLogin(username);
        setPassword(password);
        return this;
    }

    public void setLogin(String username) {
        this.username.getElement().shouldBe(Condition.visible);
        this.username.setValue(username);
        TestUtils.takeScreenshot();
        enterLogin.click();

        currentEmail.shouldBe(Condition.visible);
        assertThat(currentEmail.getText()).as("Проверить, что логин введен корректно").isEqualTo(username);
    }

    public void setPassword(String password) {
        this.passsword.getElement().shouldBe(Condition.visible);
        this.passsword.setValue(password);
        TestUtils.takeScreenshot();
        enterLogin.click();

        createLetter.getElement().shouldBe(Condition.visible);
        TestUtils.takeScreenshot();
    }

    @Step("Отправить сообщение '{message}' по адресу '{email}'")
    public void sendMessage(String email, String message) {
        createLetter.click();

        this.email.getElement().shouldBe(Condition.visible);
        this.email.setValue(email);

        this.message.setValue(message);
        TestUtils.takeScreenshot();
        send.click();

        checkSending.shouldBe(Condition.visible);
        TestUtils.takeScreenshot();
        assertThat(checkSending.getText()).as("Проверить, что письмо успешно отправлено").isEqualTo("Письмо отправлено");
    }
}
