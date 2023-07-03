package tests.wrappers;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class TextInputOther {
    String tag;
    String dataQa;

    public TextInputOther(String tag, String dataQa) {
        this.tag = tag;
        this.dataQa = dataQa;
    }

    public void write(String text) {
        $x(format("//%s[@data-qa='%s']", tag, dataQa)).setValue(text);
    }
}
