package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class ContactUsTest extends BaseTest{
    @Test
    @Description("Test for checking that user can contact with support")
    public void sendEmailForSupport() {
        contactUsPageSteps
                .openPage()
                .createNewEmailBody(chooseOS())
                .isSuccessNotificationAppeared()
                .clickOnHomeButtonAtContactUsPage();
        mainPageSteps
                .isMainPageOpened();
    }
}
