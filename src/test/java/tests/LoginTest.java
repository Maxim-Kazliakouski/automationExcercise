package tests;

import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @TmsLink("case=8")
    @Test
    @Description("Test for checking that created user can log in")
    public void login() {
        signUpPageSteps
                .openPage();
        loginPageSteps
                .loginWithCreds("123@mail.com", "123");
        mainPageSteps
                .isMainPageOpened()
                .isUserLogged("max");
    }

    @TmsLink("case=9")
    @Test
    @Description("Test for checking that user with invalid creds can't log in")
    public void loginWithInvalidCreds() {
        signUpPageSteps
                .openPage();
        loginPageSteps
                .loginWithCreds("000@mail.ru", "000")
                .checkNotificationAndColor("Your email or password is incorrect!");
    }

    @TmsLink("case=10")
    @Test
    @Description("Test for checking that user has ability to logout")
    public void logout() {
        signUpPageSteps
                .openPage();
        loginPageSteps
                .loginWithCreds("123@mail.com", "123");
        mainPageSteps
                .isMainPageOpened()
                .isUserLogged("max")
                .logout()
                .isUserLogout();
    }

    @TmsLink("11")
    @Test
    @Description("Test for checking that there is no ability to register user with existing email")
    public void existingEmail() {
        signUpPageSteps
                .openPage()
                .signUpWithCreds("max", "123@mail.com");
        loginPageSteps
                .checkNotificationAndColor("Email Address already exist!");
    }
}
