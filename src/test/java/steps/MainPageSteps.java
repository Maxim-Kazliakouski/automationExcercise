package steps;

import com.codeborne.selenide.Condition;
import pages.MainPage;
import pages.SignUpLoginPage;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class MainPageSteps {
    MainPage mainPage;
    SignUpLoginPage signUpLoginPage;

    public MainPageSteps() {
        mainPage = new MainPage();
        signUpLoginPage = new SignUpLoginPage();
    }

    public MainPageSteps openPage() {
        mainPage
                .openPage()
                .isOpened();
        return this;
    }

    public void clickOnTab(String tabName) {
        $x(format("//li/a[text()=' %s']", tabName)).shouldBe(Condition.visible);
    }

    public MainPageSteps clickOnHeaderTabs() {
        mainPage
                .clickOnEachTabAndCheckWorkability();
        return this;
    }
}
