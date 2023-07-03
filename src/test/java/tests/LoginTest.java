package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    @Description("Test for checking that created user can log in")
    public void login() {
        signUpSteps
                .openPage();
        loginSteps
                .loginWithCreds("123@mail.com", "123");
        mainPageSteps
                .isOpened()
                .isUserLogged("max");
    }

    @Test
    @Description("Test for checking that user with invalid creds can't log in")
    public void loginWithInvalidCreds() {
        signUpSteps
                .openPage();
        loginSteps
                .loginWithCreds("000@mail.ru", "000")
                .checkNotificationAndColor("Your email or password is incorrect!");
    }

    @Test
    @Description("Test for checking that user has ability to logout")
    public void logout() {
        signUpSteps
                .openPage();
        loginSteps
                .loginWithCreds("123@mail.com", "123");
        mainPageSteps
                .isOpened()
                .isUserLogged("max")
                .logout()
                .isUserLogout();
    }

    @Test
    @Description("Test for checking that there is no ability to register user with existing email")
    public void existingEmail() {
        signUpSteps
                .openPage()
                .signUpWithCreds("max", "123@mail.com");
        loginSteps
                .checkNotificationAndColor("Email Address already exist!");
    }
}
