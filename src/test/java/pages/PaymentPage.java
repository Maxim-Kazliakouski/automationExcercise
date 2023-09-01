package pages;

import com.codeborne.selenide.Condition;
import dto.Payment;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import tests.wrappers.TextInputLabel;
import tests.wrappers.TextInputOther;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static constants.PaymentPageLocators.*;

@Log4j2
public class PaymentPage extends BasePage {
    public void isOpenedPage() {
        $x(PAYMENT_PAGE_HEADER).shouldBe(Condition.visible);
    }

    public PaymentPage createPaymentMethod(Payment payment) {
        new TextInputLabel("Name on Card").write(payment.getNameOnCard());
        new TextInputLabel("Card Number").write(payment.getCardNumber());
        new TextInputLabel("CVC").write(payment.getCvc());
        new TextInputLabel("Expiration").write(payment.getExpiration().toString());
        new TextInputOther("input", "data-qa", "expiry-year").write(payment.getYear().toString());
        return this;
    }

    public PaymentPage clickOnPayAndConfirmOrder() {
        $(By.id(PAY_AND_CONFIRM_BUTTON)).shouldBe(Condition.visible).click();
        return this;
    }

    public String checkingSuccessfulMessage() {
        return $x(SUCCESSFUL_MESSAGE).shouldBe(Condition.visible).getText();
    }

    public String downloadInvoice() throws FileNotFoundException {
        File invoice = $x(DOWNLOAD_INVOICE_BUTTON).download();
        String fileContents = null;
        try (FileInputStream inputStream = new FileInputStream(invoice)) {
            fileContents = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("The invoice hasn't been downloaded");
        }
        log.info("The invoice has been downloaded!");
        return fileContents;
    }
}
