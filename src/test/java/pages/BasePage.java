package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.Alert;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;
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
}
