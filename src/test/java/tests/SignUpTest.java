package tests;

import dto.Account;
import factories.AccountFactory;
import jdk.jfr.Description;
import org.testng.annotations.Test;

public class SignUpTest extends BaseTest {

    @Test
    @Description("Test for checking 'signup/login' page is opened")
    public void openSignUpLoginPage() {
        mainPageSteps
                .openPage()
                .clickOnTab("Products");
        signUpPageSteps
                .openPage();
    }

    @Test
    @Description("Test for creating new account")
    public void createNewAccount() {
        Account account = AccountFactory.getAccount("Mrs", "Canada");
        signUpPageSteps
                .openPage()
                .createNewUser(account)
                .isAccountCreated()
                .isLoggedAsCreatedAccount()
                .isAccountDeleted();
    }
}
