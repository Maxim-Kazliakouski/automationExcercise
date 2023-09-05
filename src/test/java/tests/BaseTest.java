package tests;

//import adapters.ProjectAPI;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.*;
import steps.*;
import tests.base.TestListener;
import utils.PropertyReader;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;
import static java.lang.String.format;

@Log4j2
@Listeners(TestListener.class)
public class BaseTest {
    public MainPageSteps mainPageSteps;
    public SignUpPageSteps signUpPageSteps;
    public LoginPageSteps loginPageSteps;
    public ContactUsPageSteps contactUsPageSteps;
    public TestCasesPageSteps testCasesPageSteps;
    public ProductsPageSteps productsPageSteps;
    public ItemPageSteps itemPageSteps;
    public CartPageSteps cartPageSteps;
    public ProductsDetailsPageSteps productsDetailsPageSteps;
    public OrderCheckoutSteps orderCheckoutSteps;
    public PaymentPageSteps paymentPageSteps;

    String username;
    String password;
    public static String caseID;
    public static String testCaseName;

//        public void clearingCacheCookie() {
//        clearBrowserLocalStorage();
//        clearBrowserCookies();
//        clearBrowserCache();
//        refresh();
//    }

    @BeforeSuite
    public void preconditionBeforeAllTests() {
        log.info("Clearing folder before suite....");
        //clearing folders before starting tests...
        switch (PropertyReader.getProperty("os")) {
            case ("windows"):
                clearFolder(PropertyReader.getProperty("downloadFolderPathWindows"));
                log.info("Download folder cleared successfully");
                clearFolder(PropertyReader.getProperty("screenshotFolderWindows"));
                log.info("Screenshot folder cleared successfully");
                break;
            case ("macos"):
                clearFolder(PropertyReader.getProperty("downloadFolderPathMacOS"));
                log.info("Download folder cleared successfully");
                clearFolder(PropertyReader.getProperty("screenshotFolderMacOS"));
                log.info("Screenshot folder cleared successfully");
                break;
        }
    }

    @BeforeMethod
    public void init(ITestResult result) {

//        if (Boolean.parseBoolean(PropertyReader.getProperty("api"))) {
//        } else {
//      getting test case ID
//            Collection<String> caseIDs = scenario.getSourceTagNames();
//            for (String eachCaseID : caseIDs) {
//                caseID = eachCaseID.substring(12);
//            }
//            username = System.getProperty("USERNAME", PropertyReader.getProperty("qase.username"));
//            password = System.getProperty("PASSWORD", PropertyReader.getProperty("qase.password"));


//        DesiredCapabilities capabilities = new DesiredCapabilities();

//        System.setProperty("webdriver.http.factory", "jdk-http-client");
//        capabilities.setCapability("browserName", PropertyReader.getProperty("browser"));
//        capabilities.setCapability("browserVersion", PropertyReader.getProperty("browserVersion"));
//        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
//                "enableVNC", true,
//                "enableVideo", Boolean.parseBoolean(PropertyReader.getProperty("videoTestRecord")),
//                "enableLog", true
//        ));
//      capabilities.setCapability("logName", "my-cool-log.log");
//      capabilities.setCapability("videoScreenSize", "1920x1080");
//      capabilities.setCapability("videoName", format("%s.mp4", testCaseName));

//        Configuration.baseUrl = System.getProperty("URL", PropertyReader.getProperty("base_url"));

//      Configuration.browser = PropertyReader.getProperty("browser");
//        Configuration.headless = Boolean.parseBoolean(PropertyReader.getProperty("headless"));
//        Configuration.timeout = 10000;
        // timeout for full page loading (see on document.readyState in console, 120000 = 2 min)
//        Configuration.pageLoadTimeout = 120000;
//        Configuration.reportsFolder = "target/screenshots";
//        Configuration.savePageSource = false;
//        Configuration.downloadsFolder = PropertyReader.getProperty("downloadFolderPathWindows");
//      Configuration.browserSize = "1920x1080";
//        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
//        Configuration.remote = "http://localhost:4444/wd/hub";
//        ChromeOptions chromeOptions = new ChromeOptions();
//        switch (PropertyReader.getProperty("os")) {
//            case ("windows"):
//                chromeOptions.addArguments("--user-data-dir=C:\\Users\\Selecty\\AppData\\Local\\Google\\Chrome\\User Data");
//                chromeOptions.addArguments("--profile-directory=Default");
//                break;
//            case ("macos"):
//                chromeOptions.addArguments("--user-data-dir=/Volumes/Work/browser_profiles");
//                chromeOptions.addArguments("--profile-directory=Profile 1");
//                break;
//        }
//        chromeOptions.addArguments("--headless");

//        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
//        Configuration.browserCapabilities = capabilities;

        //for selenoid
        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("browserName", PropertyReader.getProperty("browser"));
//        capabilities.setCapability("browserVersion", PropertyReader.getProperty("browserVersion"));
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true,
                "videoName", format(result.getMethod().getMethodName() + ".mp4"),
                "enableLog", true,
                "logName", format(result.getMethod().getMethodName() + ".log")
        ));
//        capabilities.setCapability("logName", "my-cool-log.log");
//
//        System.out.println("Test case name --> " + result.getMethod().getMethodName());
//
//        ChromeOptions options = new ChromeOptions();
//        Map<String, Object> selenoidOptions = new HashMap<>();
//        selenoidOptions.put("enableVNC", true);
//        selenoidOptions.put("enableVideo", true);
//        selenoidOptions.put("enableLog", true);

//        capabilities.setCapability("logName", "my-cool-log.log");
//        capabilities.setCapability("videoScreenSize", "1920x1080");
//        capabilities.setCapability("videoName", format("%s.mp4", "test-case"));
        Configuration.baseUrl = System.getProperty("URL", PropertyReader.getProperty("base_url"));
        Configuration.timeout = 10000;
        Configuration.reportsFolder = "target/screenshots";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
        Configuration.remote = "http://localhost:4444/wd/hub";
//        Configuration.browser="chrome";
//        Configuration.browserVersion="115.0";

//        options.setCapability("selenoid:options", selenoidOptions);

        Configuration.browserCapabilities = capabilities;
        // create objects...
        mainPageSteps = new MainPageSteps();
        signUpPageSteps = new SignUpPageSteps();
        loginPageSteps = new LoginPageSteps();
        contactUsPageSteps = new ContactUsPageSteps();
        testCasesPageSteps = new TestCasesPageSteps();
        productsPageSteps = new ProductsPageSteps();
        itemPageSteps = new ItemPageSteps();
        cartPageSteps = new CartPageSteps();
        orderCheckoutSteps = new OrderCheckoutSteps();
        productsDetailsPageSteps = new ProductsDetailsPageSteps();
        paymentPageSteps = new PaymentPageSteps();
        open();
        getWebDriver().manage().window().maximize();
    }

    @AfterMethod
    public void close() {
        if (Boolean.parseBoolean(PropertyReader.getProperty("api"))) {
        } else if (getWebDriver() != null) {
            getWebDriver().quit();
        }
    }

    public String chooseOS() {
        String filePath = null;
        switch (PropertyReader.getProperty("os")) {
            case ("macos"):
                filePath = "/Volumes/Work/automationExercise/fileForUploading.txt";
                break;
            case ("windows"):
                filePath = "D:\\automationExercise\\fileForUploading.txt";
                break;
        }
        return filePath;
    }

    @AfterSuite
    public void postConditionAfterAllTests() {
        log.info("The end of performing tests in suite....");
    }

    public void clearFolder(String path) {
        try {
            FileUtils.cleanDirectory(new File(path));
        } catch (IOException e) {
            log.error("Unable to clear the folder: " + e.getMessage());
        }
    }

//    @AfterAll
//    public static void clearingProjects() {
//        Gson gson = new Gson();
//        ProjectAPI projectAPI = new ProjectAPI();
//        Response response = projectAPI.getAllProjects();
//        APIResponse<Project> result = gson.fromJson(response.asString(),
//                new TypeToken<APIResponse<Project>>() {
//                }.getType());
//        List<Entity> entities = result.getResult().entities;
//        List<String> code = entities.stream().map(Entity::getCode).collect(Collectors.toList());
//        for (String eachCode : code) {
//            if (!eachCode.equals("QASE") && !eachCode.equals("SHARELANE") && !eachCode.equals("PFT")) {
//                projectAPI.deleteProjectByCode(eachCode);
//                System.out.printf("Project '%s' has been deleted\n", eachCode);
//                log.info(format("Project '%s' has been deleted\n", eachCode));
//            }
//            else log.info("There is nothing to delete");
//        }
//    }
}