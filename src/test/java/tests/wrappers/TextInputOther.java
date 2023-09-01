package tests.wrappers;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class TextInputOther {
    String tag;
    String attributeName;
    String attributeValue;

    public TextInputOther(String tag, String attributeName, String attributeValue) {
        this.tag = tag;
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }

    public void write(String text) {
        $x(format("//%s[@%s='%s']", tag, attributeName, attributeValue)).setValue(text);
    }
}