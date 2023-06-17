package tests;

import jdk.jfr.Description;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class SignUpTest extends BaseTest {

    @Test
    @Description("Test for checking 'signup/login' page is opened")
    public void openSignUpLoginPage() {
        mainPageSteps
                .openPage()
                .clickOnTab("Products");
        signUpSteps
                .openPage();
    }

    @Test
    @Description("Test for creating new account")
    public void createNewAccount() {
        signUpSteps
                .openPage()
                .createNewUser()
                .isAccountCreated()
                .isLoggedAsCreatedAccount()
                .isAccountDeleted();
    }
}
