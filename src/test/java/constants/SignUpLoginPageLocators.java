package constants;

public class SignUpLoginPageLocators {
    //1st phase of creating new account
    public static final String SIGN_UP_NAME_FIELD = "//input[@data-qa='signup-name']";
    public static final String SIGN_UP_EMAIL_FIELD = "//input[@data-qa='signup-email']";
    public static final String SIGN_UP_BUTTON = "//button[@data-qa='signup-button']";
    public static final String LOGIN_BUTTON = "//button[@data-qa='login-button']";
    public static final String LOGIN_EMAIL_FIELD = "//input[@data-qa='login-email']";
    public static final String LOGIN_PASSWORD_FIELD = "//input[@data-qa='login-password']";
    public static final String ERROR_NOTIFICATION = "//p[text()='%s']";
    //2nd phase of creating new account
    public static final String SECOND_PHASE_PAGE_MARKER = "//b[text()='Enter Account Information']";
    public static final String CREATE_ACCOUNT_BUTTON = "//button[@data-qa='create-account']";
    // Account has been created
    public static final String ACCOUNT_CREATED_PAGE_MARKER = "//b[text()='Account Created!']";
    public static final String CONTINUE_BUTTON = "//a[@data-qa='continue-button']";
    public static final String DELETE_ACCOUNT_BUTTON = "//a[contains(text(),'Delete Account')]";
    public static final String ACCOUNT_DELETED_PAGE_MARKER = "//b[text()='Account Deleted!']";

}
