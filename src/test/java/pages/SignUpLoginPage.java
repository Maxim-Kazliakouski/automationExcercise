package pages;

import com.codeborne.selenide.Condition;
import dto.Account;
import tests.wrappers.Dropdown;
import tests.wrappers.RadioButton;
import tests.wrappers.TextInput;

import static com.codeborne.selenide.Selenide.*;
import static constants.MainPageConstants.*;
import static constants.SignUpLoginPageConstants.*;
import static java.lang.String.format;

public class SignUpLoginPage {
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
        $x(NAME_FIELD).setValue(username);
        $x(EMAIL_FIELD).setValue(account.getEmail());
        $x(SIGN_UP_BUTTON).click();
        $x(SECOND_PHASE_PAGE_MARKER).shouldBe(Condition.visible);
        new RadioButton("Title").select(account.getTitle());
        new TextInput("Password").write(account.getPassword());
        new Dropdown("Date of Birth").select("days", account.getDayOfBirth());
        new Dropdown("Date of Birth").select("months", account.getMonthOfBirth());
        new Dropdown("Date of Birth").select("years", account.getYearOfBirth());
        new TextInput("First name").write(account.getFirstName());
        new TextInput("Last name").write(account.getLastName());
        new TextInput("Company").write(account.getCompany());
        new TextInput("Address").write(account.getAddressRequired());
        new TextInput("Address 2").write(account.getAddress2());
        new Dropdown("Country").select("country", account.getCountry());
        new TextInput("State").write(account.getState());
        new TextInput("City").write(account.getCity());
        new TextInput("Zipcode").write(account.getZipCode());
        new TextInput("Mobile Number").write(account.getMobileNumber());
        $x(CREATE_ACCOUNT_BUTTON).scrollIntoView(true).click();
        $x(ACCOUNT_CREATED_PAGE_MARKER).shouldBe(Condition.visible);
    }

    public void checkCreatedAccount() {
        $x(ACCOUNT_CREATED_PAGE_MARKER).shouldBe(Condition.visible);
    }

    public void checkLoggedCreatedAccount() {
        $x(CONTINUE_BUTTON).click();
        $x(format("//b[text()='%s']", username)).shouldBe(Condition.visible);
    }

    public void deleteCratedAccount() {
        $x(DELETE_ACCOUNT_BUTTON).click();
        $x(ACCOUNT_DELETED_PAGE_MARKER).shouldBe(Condition.visible);
        $x(CONTINUE_BUTTON).click();
    }

    public void login() {

    }
}