package steps;

import dto.OrderCheckout;
import lombok.extern.log4j.Log4j2;
import pages.BasePage;
import pages.OrderCheckoutPage;

import static constants.CheckOutPageLocators.PLACE_ORDER_BUTTON;
import static java.lang.String.format;

@Log4j2
public class OrderCheckoutSteps {
    OrderCheckoutPage orderCheckoutPage;
    BasePage basePage;

    public OrderCheckoutSteps() {
        orderCheckoutPage = new OrderCheckoutPage();
        basePage = new BasePage();
    }

    public OrderCheckout getData() {
        return
                orderCheckoutPage
                        .getDataFromCreatedAccount();
    }

    public OrderCheckoutSteps enterComment(String text) {
        orderCheckoutPage
                .enterText(text);
        log.info(format("Comment '%s' has been entered!", text));
        return this;
    }

    public void clickOnPlaceOrderButton() {
        basePage.clickOnButton(PLACE_ORDER_BUTTON);
        log.info("Click on 'Place order' button");
    }
}
