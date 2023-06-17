package tests.wrappers;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class TextInput {
    String label;

    public TextInput(String label) {
        this.label = label;
    }

    public void write(String value) {
        $x(format("//label[contains(text(),'%s')]//..//input", label)).setValue(value);
    }
}
