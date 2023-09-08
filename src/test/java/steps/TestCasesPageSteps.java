package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import pages.TestCasesPage;

@Log4j2
public class TestCasesPageSteps {
    TestCasesPage testCasesPage;


    public TestCasesPageSteps() {
        testCasesPage = new TestCasesPage();
    }

    @Step("Open test cases page")
    public void openPage() {
        testCasesPage
                .openPage()
                .isOpened();
    }

    @Step("Is test case page opened")
    public void isPageOpened() {
        testCasesPage
                .isOpened();
        log.info("Test cases page is opened");
    }
}