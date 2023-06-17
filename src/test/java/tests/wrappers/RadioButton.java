package tests.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class RadioButton {
        String label;

        public RadioButton(String label) {
            this.label = label;
        }

        public void select(String value) {
            $x(format("//label[text()='%s']//following::input[@value='%s']", label, value)).click();
        }
}
