package tests;

//import adapters.ProjectAPI;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;
import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.*;
import steps.*;
import tests.base.TestListener;
import utils.PropertyReader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.lang.String.format;
import static org.testng.AssertJUnit.assertEquals;

@Log4j2
@Listeners(TestListener.class)
public class BaseTest implements ITestListener {
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
    private String testCaseName;

    public String getTestCaseName() {
        return testCaseName;
    }

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
        testCaseName = result.getMethod().getMethodName();
        log.info("TEST CASE NAME --> " + testCaseName);
        username = System.getProperty("USERNAME", PropertyReader.getProperty("qase.username"));
        password = System.getProperty("PASSWORD", PropertyReader.getProperty("qase.password"));


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
//        capabilities.setCapability("videoScreenSize", "1920x1080");
        Configuration.baseUrl = System.getProperty("URL", PropertyReader.getProperty("base_url"));
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 120000;
        Configuration.reportsFolder = "target/screenshots";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
        Configuration.remote = "http://localhost:4444/wd/hub";
//        Configuration.browser="chrome";
//        Configuration.browserVersion="115.0";


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

    public void assertScreenshots(String info) {
        String expectedFileName = info + ".png";
        String expectedScreenshotsDir = "C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\AutomationExercise\\src\\test\\resources\\expectedScreenshots\\";

        File actualScreenshot = Selenide.screenshot(OutputType.FILE);
        File expectedScreenshot = new File(expectedScreenshotsDir + expectedFileName);
        log.info("expectedScreenshot" + expectedScreenshot);
        if (!expectedScreenshot.exists()) {
            addImgToAllure("actual", actualScreenshot);
            throw new IllegalArgumentException("Can't assert image, because there is no reference. Actual screen can be downloaded from Allure");
        }
        BufferedImage expectedImage = ImageComparisonUtil.readImageFromResources(expectedScreenshotsDir + expectedFileName);
        BufferedImage actualImage = ImageComparisonUtil.readImageFromResources(actualScreenshot.toPath().toString());

        File resultDestinationDir = new File("C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\AutomationExercise\\src\\test\\resources\\screenshotsDiffs\\" + expectedFileName);
        log.info("PATH TO SCREENSHOT" + resultDestinationDir);

        ImageComparison imageComparison = new ImageComparison(expectedImage, actualImage, resultDestinationDir);
        ImageComparisonResult result = imageComparison.compareImages();

        if (!result.getImageComparisonState().equals(ImageComparisonState.MATCH)) {
            addImgToAllure("actual", actualScreenshot);
            addImgToAllure("expected", expectedScreenshot);
            addImgToAllure("diff", resultDestinationDir);
        }

        assertEquals(ImageComparisonState.MATCH, result.getImageComparisonState());
    }

    private void addImgToAllure(String name, File file) {
        try {
            byte[] image = Files.readAllBytes(file.toPath());
            saveScreenshots(name, image);
        } catch (IOException e) {
            throw new RuntimeException("Can't read bytes");
        }
    }

    @Attachment(value = "{name}", type = "image/png")
    private static byte[] saveScreenshots(String name, byte[] image) {
        return image;
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