package steps;

import dto.EmailBody;
import factories.EmailBodyFactory;
import lombok.extern.log4j.Log4j2;
import pages.ContactUsPage;

import static com.codeborne.selenide.Selenide.sleep;
@Log4j2
public class ContactUsSteps {
    ContactUsPage contactUsPage;

    public ContactUsSteps() {
        contactUsPage = new ContactUsPage();
    }

    public ContactUsSteps openPage() {
        contactUsPage
                .openPage()
                .isOpened();
        return this;
    }

    public ContactUsSteps createNewEmailBody(String absolutePathToUploadFile) {
        EmailBody newEmailBody = EmailBodyFactory.getEmailBody(absolutePathToUploadFile);
        contactUsPage
                .createEmail(newEmailBody);
        log.info("Email body has been created!");
        return this;
    }

    public ContactUsSteps isSuccessNotificationAppeared() {
        contactUsPage
                .successNotificationMessage();
        return this;
    }

    public void clickOnHomeButtonAtContactUsPage() {
        contactUsPage
                .clickOnHomeButton();
    }
}
