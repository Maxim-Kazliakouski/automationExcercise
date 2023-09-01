package steps;

import lombok.extern.log4j.Log4j2;
import pages.TestCasesPage;

@Log4j2
public class TestCasesPageSteps {
    TestCasesPage testCasesPage;

    public TestCasesPageSteps() {
        testCasesPage = new TestCasesPage();
    }
     public void openPage() {
        testCasesPage
                .openPage()
                .isOpened();
     }

     public void isPageOpened() {
        testCasesPage
                .isOpened();
        log.info("Test cases page is opened");
     }
}
