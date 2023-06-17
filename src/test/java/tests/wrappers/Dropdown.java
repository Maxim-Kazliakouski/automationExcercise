package tests.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class Dropdown {
    String label;

    public Dropdown(String label) {
        this.label = label;
    }

    public void select(String option, String value) {
        $x(format("//select[@id='%s']", option)).selectOptionByValue(value);
    }
}
