package pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static constants.MainPageLocators.TEST_CASES_PAGE_MARKER;

public class TestCasesPage {

    public TestCasesPage openPage() {
        open("/test_cases");
        return this;
    }

    public void isOpened() {
        $x(TEST_CASES_PAGE_MARKER).shouldBe(Condition.visible);
    }
}
