package tests;

//import adapters.ProjectAPI;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
//import dto.Project;
//import io.cucumber.java.After;
//import io.cucumber.java.AfterAll;
//import io.cucumber.java.Before;
//import io.cucumber.java.Scenario;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
//import tests.api.moduls.APIResponse;
//import tests.api.moduls.Project.Entity;
import steps.ContactUsSteps;
import steps.LoginSteps;
import steps.MainPageSteps;
import steps.SignUpSteps;
import tests.base.TestListener;
import utils.PropertyReader;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.lang.String.format;

@Log4j2
@Listeners(TestListener.class)
public class BaseTest {
    public MainPageSteps mainPageSteps;
    public SignUpSteps signUpSteps;
    public LoginSteps loginSteps;
    public ContactUsSteps contactUsSteps;

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
        chromeOptions.addArguments("--user-data-dir=/Volumes/Work/browser_profiles");
        chromeOptions.addArguments("--profile-directory=Profile 1");
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        Configuration.browserCapabilities = capabilities;

        // create objects...
        mainPageSteps = new MainPageSteps();
        signUpSteps = new SignUpSteps();
        loginSteps = new LoginSteps();
        contactUsSteps = new ContactUsSteps();
        open();
//        clearBrowserLocalStorage();
//        clearBrowserCookies();
        getWebDriver().manage().window().maximize();
    }

    @AfterMethod
    public void close() {
        if (Boolean.parseBoolean(PropertyReader.getProperty("api"))) {
        } else if (getWebDriver() != null) {
            getWebDriver().quit();
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