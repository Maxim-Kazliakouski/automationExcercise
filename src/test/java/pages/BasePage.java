package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Alert;
import utils.PropertyReader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;
import static constants.SignUpLoginPageLocators.ERROR_NOTIFICATION;
import static java.lang.String.format;

@Log4j2
public class BasePage {
    public void errorNotification(String text) {
        $x(format(ERROR_NOTIFICATION, text)).shouldBe(Condition.visible);
    }

    public void acceptBrowserNotification() {
        Alert alert = switchTo().alert();
        alert.accept();
    }

    public static ArrayList<String> gettingListFromTxtFile() {
        String txtFilePath = null;
        switch (PropertyReader.getProperty("os")) {
            case ("windows"):
                txtFilePath = "D:\\automationExercise\\src\\test\\java\\tests\\testData\\productsList.txt";
                break;
            case ("macos"):
                txtFilePath = "/Volumes/Work/automationExercise/src/test/java/tests/testData/productsList.txt";
                break;
        }
        ArrayList<String> dataArray = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(txtFilePath), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                dataArray.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataArray;
    }

    public void scrollToTheEndOfThePage() {
        executeJavaScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void scrollToTheCertainElement(SelenideElement element) {
        executeJavaScript("arguments[0].scrollIntoView(true);", element);
    }

    public void jsClick(SelenideElement element) {
        executeJavaScript("arguments[0].click();", element);
    }

    public Duration durationInSeconds(String value) {
        return Duration.ofSeconds(Long.parseLong(PropertyReader.getProperty(value)));
    }

    public void clickOnButton(SelenideElement element) {
        element.shouldBe(Condition.visible).click();
    }

//    public void closeAdds() {
//        try {
//            if ($x("//div[@id='dismiss-button']").isDisplayed()) {
//                $x("//div[@id='dismiss-button']").click();
//                log.info("The modal adds with 'x' button HAS BEEN CLOSED!");
//            } else {
//                log.info("The modal adds with 'x' button is absent");
//            }
//        } catch (NoSuchElementException ignored) {
//        }
//        try {
//            if ($x("//div[@class='grippy-host']").isDisplayed()) {
//                $x("//div[@class='grippy-host']").click();
//                log.info("The bottom adds HAS BEEN CLOSED!");
//            } else {
//                log.info("The bottom adds is absent");
//            }
//        } catch (NoSuchElementException ignored) {
//        }
//        try {
//            if ($x("//div[@id='dismiss-button']").isDisplayed()) {
//                $x("//div[@id='dismiss-button']").click();
//                log.info("The modal adds with 'CLose' button HAS BEEN CLOSED!");
//            } else {
//                log.info("The modal adds with 'CLose' button is absent");
//            }
//        } catch (NoSuchElementException ignored) {
//        }
//    }
}