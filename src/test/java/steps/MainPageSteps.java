package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import pages.BasePage;
import pages.MainPage;
import pages.SignUpLoginPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static constants.MainPageLocators.*;
import static java.lang.String.format;

@Log4j2
public class MainPageSteps {
    MainPage mainPage;
    SignUpLoginPage signUpLoginPage;
    BasePage basePage;

    public MainPageSteps() {
        mainPage = new MainPage();
        signUpLoginPage = new SignUpLoginPage();
        basePage = new BasePage();
    }

    @Step("Opening main page...")
    public MainPageSteps openPage() {
        log.info("Opening main page...");
        mainPage
                .openPage();
        return this;
    }

    @Step("Main page is opened")
    public MainPageSteps isMainPageOpened() {
        mainPage
                .isOpened();
        log.info("The Main page is opened");
        return this;
    }

    @Step("Click on '{tabName}' tab")
    public void clickOnTab(String tabName) {
        $x(format("//li/a[text()=' %s']", tabName)).shouldBe(visible).click();
        log.info(format("Click on '%s' tab", tabName));
    }

    @Step("CLicking on each tab in header...")
    public MainPageSteps clickOnAllHeaderTabs() {
        log.info("CLicking on each tab in header...");
        mainPage
                .clickOnEachTabAndCheckWorkability();
        return this;
    }

    @Step("User '{username}' is logged!")
    public MainPageSteps isUserLogged(String username) {
        mainPage
                .checkLoginUser(username);
        log.info(format("User '%s' is logged!", username));
        return this;
    }

    @Step("Logout user")
    public MainPageSteps logout() {
        mainPage
                .logout();
        return this;
    }

    @Step("User has been logout")
    public void isUserLogout() {
        mainPage
                .checkThatUserLogout();
        log.info("User has been logout!");
    }

    @Step("Subscribe at the page footer")
    public void toSubscribe(String emailForSubscribing) {
        mainPage
                .toSubscribeAtFooterOfThePage(emailForSubscribing)
                .isSubscribed();
        log.info("Subscribe at the page footer");
    }

    @Step("Click on '{categoryName}' category")
    public MainPageSteps clickOnCategory(String categoryName) {
        mainPage
                .clickOnCategory(categoryName);
        log.info(format("Click on '%s' category", categoryName));
        return this;
    }

    @Step("Click on subcategory '{subcategoryName}'")
    public MainPageSteps clickOnSubcategory(String categoryName, String subcategoryName) {
        mainPage
                .clickOnSubCategory(categoryName, subcategoryName);
        log.info(format("Click on subcategory '%s'", subcategoryName));
        return this;
    }

    @Step("Check product header")
    public String checkProductHeader() {
        return
                mainPage
                        .getProductHeader();
    }

    @Step("Scroll to the 'RECOMMENDED ITEMS header...")
    public MainPageSteps scrollToTheRecommendedItemsHeader() {
        log.info("Scroll to the 'RECOMMENDED ITEMS header...'");
        basePage
                .scrollToTheCertainElement($x(RECOMMENDED_ITEMS_HEADER));
        return this;
    }

    @Step("Is recommended items visible")
    public MainPageSteps isRecommendedItemsIsVisible() {
        mainPage
                .checkRecommendedItemsHeader();
        log.info("Scroll to the 'RECOMMENDED ITEMS header has been successful'");
        return this;
    }

    @Step("Choose recommended item --> {recommendedItemName}")
    public void chooseRecommendedItem(String recommendedItemName) {
        log.info(format("Choose recommended item --> %s", recommendedItemName));
        mainPage
                .chooseRecommendedItem(recommendedItemName);
    }
}
