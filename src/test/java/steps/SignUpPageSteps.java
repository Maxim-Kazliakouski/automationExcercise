package steps;

import dto.Account;
import factories.AccountFactory;
import pages.SignUpLoginPage;

public class SignUpPageSteps {
    SignUpLoginPage signUpLoginPage;

    public SignUpPageSteps() {
        signUpLoginPage = new SignUpLoginPage();
    }

    public SignUpPageSteps openPage() {
        signUpLoginPage
                .openPage()
                .isPageOpened();
        return this;
    }

    public SignUpPageSteps createNewUser(Account newAccount) {
        signUpLoginPage
                .createNewAccount(newAccount);
        return this;
    }

    public SignUpPageSteps isAccountCreated() {
        signUpLoginPage
                .checkCreatedAccount();
        return this;
    }

    public SignUpPageSteps isLoggedAsCreatedAccount() {
        signUpLoginPage
                .checkLoggedCreatedAccount();
        return this;
    }

    public void isAccountDeleted() {
        signUpLoginPage
                .deleteCratedAccount();
    }

    public SignUpPageSteps signUpWithCreds(String name, String email) {
        signUpLoginPage
                .signUp(name, email);
        return this;
    }
}
