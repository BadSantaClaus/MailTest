package org.example.mailtest.elements;

import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.mailtest.utils.TestUtils;
import org.openqa.selenium.Keys;

@Getter
@AllArgsConstructor
public class UiTextBox {

    public SelenideElement element;
    public String name;

    public void setValue(String value) {
        if (!noNeedChange(value)) {
            clear();
            TestUtils.step(String.format("Ввести значение '%s' в поле '%s'", value, name));
            element.scrollIntoView(true).setValue(value);
            return;
        }
        TestUtils.step(String.format("Ввести значение '%s' в поле '%s'", value, name));
    }

    public boolean noNeedChange(String value) {
        return value.equals(getValue());
    }

    public String getValue() {
        return element.getValue();
    }

    public void clear() {
        TestUtils.step(String.format("Очистить поле '%s'", name));
        element.scrollIntoView(true).setValue(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
    }

}
