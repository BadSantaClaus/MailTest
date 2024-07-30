package org.example.mailtest.page.mail_ru;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.example.mailtest.elements.UiButton;
import org.example.mailtest.elements.UiTextBox;
import org.example.mailtest.utils.TestUtils;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.assertj.core.api.Assertions.assertThat;

@Component
public class MailPage {

    private final UiButton send = new UiButton($x("//button[@data-test-id = 'send']"), "Отправить");

    private final UiTextBox email = new UiTextBox($x("//div[text() = 'Кому']//following::input"), "Кому");
    private final UiTextBox message = new UiTextBox($x("//div[contains(@class, 'editor_container')]//div[@role='textbox']"), "Сообщение");

    private final SelenideElement checkSending = $x("//span[@data-qa-id='message']");

    public void sendMessage(String email, String message) {
        switchTo().window(1);
        this.email.getElement().shouldBe(Condition.visible);
        this.email.setValue(email);

        this.message.setValue(message);
        this.email.getElement().scrollIntoView(true);
        TestUtils.takeScreenshot("Ввод почты и сообщения");
        send.click();

        checkSending.shouldBe(Condition.visible);
        TestUtils.takeScreenshot("Статус отправки сообщения");
        assertThat(checkSending.getText()).as("Проверить, что письмо успешно отправлено").isEqualTo("Письмо отправлено");
    }
}
