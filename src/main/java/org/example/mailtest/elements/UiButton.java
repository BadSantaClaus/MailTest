package org.example.mailtest.elements;

import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.mailtest.utils.TestUtils;

@Getter
@AllArgsConstructor
public class UiButton {

    private SelenideElement element;
    private String name;

    public void click() {
        TestUtils.step(String.format("Кликнуть по кнопке '%s'", name));
        element.click();
    }

}
