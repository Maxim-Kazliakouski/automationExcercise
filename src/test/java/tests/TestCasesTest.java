package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class TestCasesTest extends BaseTest {
    @Test
    @Description("Test for checking opening test cases page")
    public void openTestCasesPage() {
        mainPageSteps
                .openPage()
                .clickOnTab("Test Cases");
        testCasesPageSteps
                .isPageOpened();
    }
}
