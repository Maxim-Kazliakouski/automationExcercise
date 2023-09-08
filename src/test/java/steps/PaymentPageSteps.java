package steps;

import dto.Payment;
import io.qameta.allure.Step;
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

    @Step("Open payment page")
    public PaymentPageSteps isPaymentPageIsOpened() {
        paymentPage
                .isOpenedPage();
        log.info("Payment page is opened!");
        return this;
    }

    @Step("Creating new payment")
    public PaymentPageSteps createNewPayment(Payment payment) {
        log.info("Creating new payment...");
        paymentPage
                .createPaymentMethod(payment)
                .clickOnPayAndConfirmOrder();
        log.info("Click on 'Pay and confirm order' button");
        return this;
    }

    @Step("Getting successful message")
    public String gettingSuccessfulMessage() {
        return
                paymentPage
                        .checkingSuccessfulMessage();
    }

    @Step("Getting invoice")
    public String gettingInvoice() throws FileNotFoundException {
        return
                paymentPage
                        .downloadInvoice();
    }

    @Step("Click on continue button")
    public void clickOnContinueButton() {
        basePage
                .clickOnButton($x(CONTINUE_BUTTON));
    }
}