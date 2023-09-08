package tests;

import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class TestCasesTest extends BaseTest {
    @TmsLink("case=12")
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
