package tests;

import jdk.jfr.Description;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.sleep;

public class MainPageTest extends BaseTest {
//    @DataProvider
//    public Object[][] tabsNames() {
//        return new Object[][] {
//                {"Products"},
//                {"Cart"},
//                {"Signup / Login"},
//                {"API Testing"},
//                {"Video Tutorials"},
//                {"Contact us"}
//        };
//    }

    @Test
    @Description("Test for checking opening main page")
    public void openMainPage() {
        mainPageSteps
                .openPage();
    }

    @Test()
    @Description("Test for checking workability all tabs in app header")
    public void checkAllTabsInHeader() {
        mainPageSteps
                .openPage()
                .clickOnAllHeaderTabs();
    }

    @Test
    @Description("Test for checking subscription at home page")
    public void subscriptionAtMainPage() {
        mainPageSteps
                .openPage()
                .toSubscribe("123@mail.co");
    }
}