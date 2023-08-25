package tests;

//import adapters.ProjectAPI;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import steps.*;
import tests.base.TestListener;
import utils.PropertyReader;


import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

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

    String username;
    String password;
    public static String caseID;
    public static String testCaseName;

    @BeforeMethod
    public void init() {

//        if (Boolean.parseBoolean(PropertyReader.getProperty("api"))) {
//        } else {
//      getting test case ID
//            Collection<String> caseIDs = scenario.getSourceTagNames();
//            for (String eachCaseID : caseIDs) {
//                caseID = eachCaseID.substring(12);
//            }
//            username = System.getProperty("USERNAME", PropertyReader.getProperty("qase.username"));
//            password = System.getProperty("PASSWORD", PropertyReader.getProperty("qase.password"));


        DesiredCapabilities capabilities = new DesiredCapabilities();

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

        Configuration.baseUrl = System.getProperty("URL", PropertyReader.getProperty("base_url"));
//
//            Configuration.browser = PropertyReader.getProperty("browser");
        Configuration.headless = Boolean.parseBoolean(PropertyReader.getProperty("headless"));
        Configuration.timeout = 10000;

        Configuration.reportsFolder = "target/screenshots";
//        Configuration.browserSize = "1920x1080";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
//       Configuration.remote = "http://localhost:4444/wd/hub";
//        options.addExtensions(new File("D:\\automationExcercise\\adblocker.com.crx"));

//        options.addArguments("--headless");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-password-manager-reauthentication");
        switch (PropertyReader.getProperty("os")) {
            case ("windows"):
                chromeOptions.addArguments("--user-data-dir=");
                break;
            case ("macos"):
                chromeOptions.addArguments("--user-data-dir=/Volumes/Work/browser_profiles");
                chromeOptions.addArguments("--profile-directory=Profile 1");
                break;
        }
//        chromeOptions.addArguments("--profile-directory=Profile 1");

        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);


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
        open("/");
        clearBrowserLocalStorage();
        clearBrowserCookies();
        clearBrowserCache();
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
                filePath = "/Volumes/Work/automationExcercise/fileForUploading.txt";
                break;
            case ("windows"):
                filePath = "D\\automationExcercise\\fileForUploading.txt";
                break;
        }
        return filePath;
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