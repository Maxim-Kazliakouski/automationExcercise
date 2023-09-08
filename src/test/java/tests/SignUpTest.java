package tests;

import dto.Account;
import factories.AccountFactory;
import io.qameta.allure.TmsLink;
import jdk.jfr.Description;
import org.testng.annotations.Test;

public class SignUpTest extends BaseTest {

    @TmsLink("case=6")
    @Test
    @Description("Test for checking 'signup/login' page is opened")
    public void openSignUpLoginPage() {
        mainPageSteps
                .openPage()
                .clickOnTab("Products");
        signUpPageSteps
                .openPage();
    }

    @TmsLink("case=7")
    @Test
    @Description("Test for creating new account")
    public void createNewAccount() {
        Account account = AccountFactory.getAccount("Mrs", "Canada");
        signUpPageSteps
                .openPage()
                .createNewUser(account)
                .isAccountCreated()
                .isLoggedAsCreatedAccount()
                .deleteAccount();
    }
}
