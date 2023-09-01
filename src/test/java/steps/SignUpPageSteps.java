package steps;

import dto.Account;
import lombok.extern.log4j.Log4j2;
import pages.SignUpLoginPage;

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

    public SignUpPageSteps isSignUpLoginPageOpened() {
        signUpLoginPage
                .isPageOpened();
        log.info("Sign Up / Login page is opened");
        return this;
    }

    public SignUpPageSteps createNewUser(Account newAccount) {
        log.info("Creating new account...");
        signUpLoginPage
                .createNewAccount(newAccount);
        return this;
    }

    public SignUpPageSteps isAccountCreated() {
        signUpLoginPage
                .checkCreatedAccount();
        log.info("New account has been created!");
        return this;
    }

    public SignUpPageSteps isLoggedAsCreatedAccount() {
        signUpLoginPage
                .checkLoggedCreatedAccount();
        log.info("New created account has been logged with new creds!");
        return this;
    }

    public void isLoggedAccount(String username) {
        signUpLoginPage
                .checkLoggedAccount(username);
        log.info("Account has been logged");
    }

    public void deleteAccount() {
        signUpLoginPage
                .deleteCratedAccount();
        log.info("Account has been deleted!");
    }

    public SignUpPageSteps signUpWithCreds(String name, String email) {
        signUpLoginPage
                .signUp(name, email);
        log.info("User has been signed up");
        return this;
    }
}