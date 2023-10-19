package steps;

import dto.Account;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import pages.SignUpLoginPage;

import static com.codeborne.selenide.Selenide.sleep;

@Log4j2
public class SignUpPageSteps {
    SignUpLoginPage signUpLoginPage;

    public SignUpPageSteps() {
        signUpLoginPage = new SignUpLoginPage();
    }

    public SignUpPageSteps openPage() {
        log.info("Opening SignUp / Login page...");
        signUpLoginPage
                .openPage();
        return this;
    }

    @Step("Is signup/login page opened")
    public SignUpPageSteps isSignUpLoginPageOpened() {
        signUpLoginPage
                .isPageOpened();
        log.info("Sign Up / Login page is opened");
        return this;
    }

    @Step("Create new account")
    public SignUpPageSteps createNewUser(Account newAccount) {
        log.info("Creating new account...");
        signUpLoginPage
                .createNewAccount(newAccount);
        return this;
    }

    @Step("Check that new account has been created")
    public SignUpPageSteps isAccountCreated() {
        signUpLoginPage
                .checkCreatedAccount();
        log.info("New account has been created!");
        return this;
    }

    @Step("Check that created account is logged")
    public SignUpPageSteps isLoggedAsCreatedAccount() {
        signUpLoginPage
                .checkLoggedCreatedAccount();
        log.info("New created account has been logged with new creds!");
        return this;
    }

    @Step("Check that created account '{username}' is logged")
    public void isLoggedAccount(String username) {
        signUpLoginPage
                .checkLoggedAccount(username);
        log.info("Account has been logged");
    }

    @Step("Delete account")
    public void deleteAccount() {
        signUpLoginPage
                .deleteCratedAccount();
        log.info("Account has been deleted!");
        sleep(100000);
    }

    @Step("Sign up with the creds: email -> {email}, password -> {password}")
    public SignUpPageSteps signUpWithCreds(String name, String email) {
        signUpLoginPage
                .signUp(name, email);
        log.info("User has been signed up");
        return this;
    }
}