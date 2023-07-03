package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.sleep;

public class ContactUsTest extends BaseTest{
    @Test
    @Description("Test for checking that user can contact with support")
    public void sendEmailForSupport() {
        contactUsSteps
                .openPage()
                .createNewEmailBody("/Volumes/Work/automationExcercise/fileForUploading.txt")
                .isSuccessNotificationAppeared()
                .clickOnHomeButtonAtContactUsPage();
        mainPageSteps
                .isOpened();
    }
}
