package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;
import static constants.MainPageLocators.*;
import static java.lang.String.format;

public class MainPage {

    public MainPage openPage() {
        open("/");
        return this;
    }

    public MainPage isOpened() {
        $x(HOME_BUTTON).shouldBe(Condition.visible);
        return this;
    }

    public MainPage clickOnEachTabAndCheckWorkability() {
        System.out.println("The list of tabs names --> " + gettingAllTabsNames());
        for (String tabNameFromTheList : gettingAllTabsNames()) {
            $x(format(TAB_IN_HEADER, tabNameFromTheList)).click();
            switch (tabNameFromTheList) {
                case ("Products"):
                    $x(PRODUCTS_PAGE_MARKER).shouldBe(Condition.visible);
                    break;
                case ("Cart"):
                    $x(CART_PAGE_MARKER).shouldBe(Condition.visible);
                    break;
                case ("Signup / Login"):
                    $x(SIGN_UP_LOGIN_PAGE_MARKER).shouldBe(Condition.visible);
                    break;
                case("Test Cases"):
                    $x(TEST_CASES_PAGE_MARKER).shouldBe(Condition.visible);
                    break;
                case("API Testing"):
                    $x(API_TESTING_PAGE_MARKER).shouldBe(Condition.visible);
                    break;
//                case("Video Tutorials"):
                case("Contact us"):
                    $x(CONTACT_US_PAGE_MARKER).shouldBe(Condition.visible);
                    break;
            }
        }
        return this;
    }

    public ArrayList<String> gettingAllTabsNames() {
        ElementsCollection tabsNames = $$x("//ul[@class='nav navbar-nav']//li/a");
        ArrayList<String> tabNamesList = new ArrayList<>();
        for (SelenideElement tabName : tabsNames) {
            if (tabName.getText().contains("Home") || tabName.getText().contains("Video Tutorials")) {
                continue;
            }
            if (tabName.getText().contains("Products")) {
                String clearTabName = tabName.getText().substring(2);
                tabNamesList.add(clearTabName);
            } else tabNamesList.add(tabName.getText());
        }
        return tabNamesList;
    }

    public void checkLoginUser(String username) {
        $x(format(LOGGED_USER_NAME, username)).shouldBe(Condition.visible);
    }

    public void logout() {
        $x(LOGOUT_BUTTON).click();
    }

    public void checkThatUserLogout() {
        $x(SIGN_UP_LOGIN_PAGE_MARKER).shouldBe(Condition.visible);
        $x(SIGNUP_LOGIN_BUTTON).shouldBe(Condition.visible);
    }
}
