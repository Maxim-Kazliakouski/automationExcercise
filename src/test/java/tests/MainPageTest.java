package tests;

import jdk.jfr.Description;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.sleep;
import static org.testng.Assert.assertEquals;

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

    @Test
    @Description("View Category Products")
    public void viewCategoryProducts() {
        String categoryName1 = "Men";
        String subcategoryName1 = "Tshirts";
        String categoryName2 = "Kids";
        String subcategoryName2 = "Dress";
        mainPageSteps
                .openPage()
                .isMainPageOpened()
                .clickOnCategory("Men")
                .clickOnSubcategory(categoryName1, subcategoryName1);
        assertEquals(mainPageSteps.checkProductHeader(), (categoryName1 + " - " + subcategoryName1 + " products").toUpperCase());
        mainPageSteps
                .clickOnCategory(categoryName2)
                .clickOnSubcategory(categoryName2, subcategoryName2);
        assertEquals(mainPageSteps.checkProductHeader(), (categoryName2 + " - " + subcategoryName2 + " products").toUpperCase());
    }
}