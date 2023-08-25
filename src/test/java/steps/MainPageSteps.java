package steps;

import com.codeborne.selenide.Condition;
import lombok.extern.log4j.Log4j2;
import pages.MainPage;
import pages.SignUpLoginPage;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

@Log4j2
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

    public MainPageSteps isMainPageOpened() {
        mainPage
                .isOpened();
        return this;
    }

    public void clickOnTab(String tabName) {
        $x(format("//li/a[text()=' %s']", tabName)).shouldBe(Condition.visible).click();
    }

    public MainPageSteps clickOnAllHeaderTabs() {
        mainPage
                .clickOnEachTabAndCheckWorkability();
        return this;
    }

    public MainPageSteps isUserLogged(String username) {
        mainPage
                .checkLoginUser(username);
        log.info(format("User '%s' is logged!", username));
        return this;
    }

    public MainPageSteps logout() {
        mainPage
                .logout();
        return this;
    }

    public void isUserLogout() {
        mainPage
                .checkThatUserLogout();
    }

    public void toSubscribe(String emailForSubscribing) {
        mainPage
                .toSubscribeAtFooterOfThePage(emailForSubscribing)
                .isSubscribed();
    }
}
