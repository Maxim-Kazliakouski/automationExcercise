package steps;

import pages.TestCasesPage;

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
     }
}
