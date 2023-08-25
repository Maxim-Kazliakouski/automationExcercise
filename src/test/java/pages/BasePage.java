package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import utils.PropertyReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static constants.SignUpLoginPageLocators.ERROR_NOTIFICATION;
import static java.lang.String.format;

public class BasePage {
    public void errorNotification(String text) {
        $x(format(ERROR_NOTIFICATION, text)).shouldBe(Condition.visible);
    }

    public void acceptBrowserNotification() {
        Alert alert = switchTo().alert();
        alert.accept();
    }

    public static ArrayList<String> gettingListFromTxtFile() {
        ArrayList<String> dataArray = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("/Volumes/Work/automationExcercise/src/test/java/tests/testData/productsList.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                dataArray.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataArray;
    }

    public void scrollToTheEndOfAPage() {
        executeJavaScript("window.scrollTo(0, document.body.scrollHeight)");
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
}

