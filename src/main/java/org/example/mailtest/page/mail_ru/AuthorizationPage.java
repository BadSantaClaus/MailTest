package org.example.mailtest.page.mail_ru;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.example.mailtest.elements.UiButton;
import org.example.mailtest.elements.UiTextBox;
import org.example.mailtest.utils.TestUtils;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

@Getter
@Component
public class AuthorizationPage {

    private final UiButton enter = new UiButton($x("//button[text() = 'Войти']"), "Войти");
    private final UiButton enterLogin = new UiButton($x("//span[text()= 'Войти']"), "Войти");
    private final UiButton createLetter = new UiButton($x("//a[contains(text(), 'Написать письмо')]"), "Написать письмо");

    private final UiTextBox username = new UiTextBox($x("//input[@placeholder = 'Имя аккаунта']"), "Имя аккаунта");
    private final UiTextBox password = new UiTextBox($x("//input[@placeholder = 'Пароль']"), "Пароль");

    private final SelenideElement currentEmail = $x("//h4[@data-test-id='current-email']");
    private final SelenideElement accountFrame = $x("//iframe[contains(@src, 'account.mail.ru')]");

    public void authorize(String username, String password) {
        open("");
        enter.click();

        switchTo().frame(accountFrame);
        setLogin(username);

        setPassword(password);
    }

    public void setLogin(String username) {
        this.username.getElement().shouldBe(Condition.visible);
        this.username.setValue(username);
        TestUtils.takeScreenshot("Ввод имени пользователя");
        enterLogin.click();

        currentEmail.shouldBe(Condition.visible);
        assertThat(currentEmail.getText()).as("Проверить, что логин введен корректно").isEqualTo(username);
    }

    public void setPassword(String password) {
        this.password.getElement().shouldBe(Condition.visible);
        this.password.setValue(password);
        TestUtils.takeScreenshot("Ввод пароля");
        enterLogin.click();

        createLetter.getElement().shouldBe(Condition.visible);
        TestUtils.takeScreenshot("Статус авторизации");
    }
}
