package steps;

import dto.Account;
import factories.AccountFactory;
import pages.SignUpLoginPage;

public class SignUpSteps {
    SignUpLoginPage signUpLoginPage;

    public SignUpSteps() {
        signUpLoginPage = new SignUpLoginPage();
    }

    public SignUpSteps openPage() {
        signUpLoginPage
                .openPage()
                .isPageOpened();
        return this;
    }

    public SignUpSteps createNewUser() {
        Account newAccount = AccountFactory.getAccount("Mrs", "5", "9", "1991", "Canada");
        signUpLoginPage
                .createNewAccount(newAccount);
        return this;
    }

    public SignUpSteps isAccountCreated() {
        signUpLoginPage
                .checkCreatedAccount();
        return this;
    }

    public SignUpSteps isLoggedAsCreatedAccount() {
        signUpLoginPage
                .checkLoggedCreatedAccount();
        return this;
    }

    public void isAccountDeleted() {
        signUpLoginPage
                .deleteCratedAccount();
    }
}
