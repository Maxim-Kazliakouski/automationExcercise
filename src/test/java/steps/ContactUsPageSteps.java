package steps;

import dto.EmailBody;
import factories.EmailBodyFactory;
import lombok.extern.log4j.Log4j2;
import pages.ContactUsPage;

@Log4j2
public class ContactUsPageSteps {
    ContactUsPage contactUsPage;

    public ContactUsPageSteps() {
        contactUsPage = new ContactUsPage();
    }

    public ContactUsPageSteps openPage() {
        contactUsPage
                .openPage()
                .isOpened();
        log.info("Contact page is opened");
        return this;
    }

    public ContactUsPageSteps createNewEmailBody(String absolutePathToUploadFile) {
        EmailBody newEmailBody = EmailBodyFactory.getEmailBody(absolutePathToUploadFile);
        contactUsPage
                .createEmail(newEmailBody);
        log.info("Email body has been created!");
        return this;
    }

    public ContactUsPageSteps isSuccessNotificationAppeared() {
        contactUsPage
                .successNotificationMessage();
        log.info("Successful notification is appeared");
        return this;
    }

    public void clickOnHomeButtonAtContactUsPage() {
        contactUsPage
                .clickOnHomeButton();
        log.info("Click on 'Home' button");
    }
}