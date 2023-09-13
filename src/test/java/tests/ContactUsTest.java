package tests;

import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.TestInfo;
import org.testng.annotations.Test;

public class ContactUsTest extends BaseTest {
    @TmsLink("case=1")
    @Test
    @Description("Test for checking that user can contact with support")
    public void sendEmailForSupport(TestInfo info) {
        assertScreenshots(info);
        contactUsPageSteps
                .openPage()
                .createNewEmailBody(chooseOS())
                .isSuccessNotificationAppeared()
                .clickOnHomeButtonAtContactUsPage();
        mainPageSteps
                .isMainPageOpened();
    }
}