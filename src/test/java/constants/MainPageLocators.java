package constants;

import static java.lang.String.format;

public class MainPageLocators {
    // Locators for buttons in app header
    public static final String TAB_IN_HEADER = "//li/a[contains(text(),'%s')]";
    public static final String HOME_BUTTON = "//li/a[text()=' Home']";
    public static final String LOGGED_USER_NAME = "//a[text()=' Logged in as ']//b[text()='%s']";
//    public static final String PRODUCTS_BUTTON = "//li/a[text()=' Products']";
    public static final String CART_BUTTON = "//li/a[text()=' Cart']";
    public static final String SIGNUP_LOGIN_BUTTON = "//li/a[text()=' Signup / Login']";
    public static final String TEST_CASES_BUTTON = "//li/a[text()=' Test Cases']";
    public static final String API_TESTING_BUTTON = "//li/a[text()=' API Testing']";
    public static final String CONTACT_US_BUTTON = "//li/a[text()=' Contact us']";
    public static final String LOGOUT_BUTTON = "//li//a[text()=' Logout']";

    // markers to recognize that certain page is opened
    public static final String HOME_PAGE_MARKER = "//h2[text()='Feature Items']";
    public static final String PRODUCTS_PAGE_MARKER = "//h2[text()='Category']";
    public static final String CART_PAGE_MARKER = "//b[text()='Cart is empty!']";
    public static final String SIGN_UP_LOGIN_PAGE_MARKER = "//h2[text()='Login to your account']";
    public static final String TEST_CASES_PAGE_MARKER = "//b[text()='Test Cases']";
    public static final String API_TESTING_PAGE_MARKER = "//b[text()='APIs List for practice']";
    public static final String CONTACT_US_PAGE_MARKER = "//h2[text()='Contact ']";
}
