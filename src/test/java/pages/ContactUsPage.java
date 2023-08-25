package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import dto.EmailBody;
import lombok.extern.log4j.Log4j2;
import tests.wrappers.TextInputOther;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;
import static constants.ContactUsPageLocators.*;
import static constants.MainPageLocators.CONTACT_US_PAGE_MARKER;

@Log4j2
public class ContactUsPage extends BasePage{

    public ContactUsPage openPage() {
        open("/contact_us");
        return this;
    }

    public ContactUsPage isOpened() {
        $x(CONTACT_US_PAGE_MARKER).shouldBe(Condition.visible);
        return this;
    }

    public void createEmail(EmailBody emailBody) {
        new TextInputOther("input", "name").write(emailBody.getName());
        new TextInputOther("input", "email").write(emailBody.getEmail());
        new TextInputOther("input", "subject").write(emailBody.getSubject());
        new TextInputOther("textarea", "message").write(emailBody.getTextMessage());
        SelenideElement fileInput = $x("//input[@type='file']").setValue(emailBody.getUploadingFile());
        fileInput.uploadFile(new File(emailBody.getUploadingFile()));
        $x(CONTACT_US_SUBMIT_BUTTON).click();
        acceptBrowserNotification();
    }

    public void successNotificationMessage() {
        $x(SUCCESS_NOTIFICATION).shouldBe(Condition.visible);
    }

    public void clickOnHomeButton() {
        $(HOME_BUTTON).click();
//        $x(HOME_PAGE_MARKER).shouldBe(Condition.visible);
    }
}
