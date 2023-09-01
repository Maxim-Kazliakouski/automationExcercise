package steps;

import dto.Payment;
import lombok.extern.log4j.Log4j2;
import pages.BasePage;
import pages.PaymentPage;

import java.io.FileNotFoundException;

import static com.codeborne.selenide.Selenide.$x;
import static constants.SignUpLoginPageLocators.CONTINUE_BUTTON;

@Log4j2
public class PaymentPageSteps {
    PaymentPage paymentPage;
    BasePage basePage;

    public PaymentPageSteps() {
        paymentPage = new PaymentPage();
        basePage = new BasePage();
    }

    public PaymentPageSteps isPaymentPageIsOpened() {
        paymentPage
                .isOpenedPage();
        log.info("Payment page is opened!");
        return this;
    }

    public PaymentPageSteps createNewPayment(Payment payment) {
        log.info("Creating new payment...");
        paymentPage
                .createPaymentMethod(payment)
                .clickOnPayAndConfirmOrder();
        log.info("Click on 'Pay and confirm order' button");
        return this;
    }

    public String gettingSuccessfulMessage() {
        return
                paymentPage
                        .checkingSuccessfulMessage();
    }

    public String gettingInvoice() throws FileNotFoundException {
        return
                paymentPage
                        .downloadInvoice();
    }

    public void clickOnContinueButton() {
        basePage
                .clickOnButton($x(CONTINUE_BUTTON));
    }
}