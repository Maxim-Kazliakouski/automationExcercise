package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import dto.Account;
import lombok.extern.log4j.Log4j2;
import tests.wrappers.Dropdown;
import tests.wrappers.RadioButton;
import tests.wrappers.TextInputOther;
import tests.wrappers.TextInputLabel;

import static com.codeborne.selenide.Selenide.*;
import static constants.MainPageLocators.*;
import static constants.SignUpLoginPageLocators.*;
import static java.lang.String.format;
import static org.testng.AssertJUnit.assertEquals;

@Log4j2
public class SignUpLoginPage extends BasePage{
    String username;

    public SignUpLoginPage openPage() {
        open("/login");
        return this;
    }

    public void isPageOpened() {
        $x(SIGN_UP_LOGIN_PAGE_MARKER).shouldBe(Condition.visible);
    }

    public void createNewAccount(Account account) {
        username = account.getName();
        signUp(username, account.getEmail());
        log.info(format("Email --> %s", account.getEmail()));
        $x(SECOND_PHASE_PAGE_MARKER).shouldBe(Condition.visible);
        new RadioButton("Title").select(account.getGender());
        new TextInputLabel("Password").write(account.getPassword());
        log.info(format("Password --> %s", account.getPassword()));
        new Dropdown("Date of Birth").select("days", account.getDayOfBirth());
        new Dropdown("Date of Birth").select("months", account.getMonthOfBirth());
        new Dropdown("Date of Birth").select("years", account.getYearOfBirth());
        new TextInputLabel("First name").write(account.getFirstName());
        new TextInputLabel("Last name").write(account.getLastName());
        new TextInputLabel("Company").write(account.getCompany());
        new TextInputLabel("Address").write(account.getAddressRequired());
        new TextInputLabel("Address 2").write(account.getAddress2());
        new Dropdown("Country").select("country", account.getCountry());
        new TextInputLabel("State").write(account.getState());
        new TextInputLabel("City").write(account.getCity());
        new TextInputLabel("Zipcode").write(account.getZipCode());
        new TextInputLabel("Mobile Number").write(account.getMobileNumber());
        $x(CREATE_ACCOUNT_BUTTON).scrollIntoView(true).click();
        $x(ACCOUNT_CREATED_PAGE_MARKER).shouldBe(Condition.visible);
    }

    public void checkCreatedAccount() {
        $x(ACCOUNT_CREATED_PAGE_MARKER).shouldBe(Condition.visible);
    }

    public void checkLoggedCreatedAccount() {
        $x(CONTINUE_BUTTON).click();
        $x(format(LOGGED_USER_NAME, username)).shouldBe(Condition.visible);
    }

    public void checkLoggedAccount(String username) {
        $x(format(LOGGED_USER_NAME, username)).shouldBe(Condition.visible);
    }

    public void deleteCratedAccount() {
        $x(DELETE_ACCOUNT_BUTTON).click();
        $x(ACCOUNT_DELETED_PAGE_MARKER).shouldBe(Condition.visible);
        $x(CONTINUE_BUTTON).click();
    }

    public void login(String email, String password) {
        new TextInputOther("input", "data-qa","login-email").write(email);
        new TextInputOther("input", "data-qa","login-password").write(password);
//        $x(LOGIN_EMAIL_FIELD).sendKeys(email);
//        $x(LOGIN_PASSWORD_FIELD).sendKeys(password);
        $x(LOGIN_BUTTON).click();
    }

    public void signUp(String name, String email) {
        new TextInputOther("input", "data-qa","signup-name").write(name);
        new TextInputOther("input", "data-qa","signup-email").write(email);
        $x(SIGN_UP_BUTTON).click();
    }

    public SignUpLoginPage checkErrorNotification(String notificationText) {
        errorNotification(notificationText);
        return this;
    }

    public void getCssValue(SelenideElement element, String cssParameter) {
        String valueCssParameter = element.getCssValue(cssParameter);
        assertEquals(format("The css parameter '%s' isn't correct! Actual css value --> %s", cssParameter, valueCssParameter), "rgba(255, 0, 0, 1)", valueCssParameter);
        log.info(format("The css parameter '%s' is correct with value '%s'!", cssParameter, valueCssParameter));
    }
}