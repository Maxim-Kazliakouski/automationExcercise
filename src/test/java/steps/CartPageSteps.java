package steps;

import com.codeborne.selenide.Condition;
import lombok.extern.log4j.Log4j2;
import pages.BasePage;
import pages.CartPage;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;
import static constants.CartPageLocators.*;
import static java.lang.String.format;

@Log4j2
public class CartPageSteps {
    CartPage cartPage;
    BasePage basePage;

    public CartPageSteps() {
        cartPage = new CartPage();
        basePage = new BasePage();
    }

    public CartPageSteps openCartPage() {
        log.info("Opening cart page...");
        open("/view_cart");
        return this;
    }

    public CartPageSteps isCartPageOpened() {
        cartPage
                .isOpened();
        log.info("Cart page is opened");
        return this;
    }

    public void toSubscribe(String emailForSubscribing) {
        cartPage
                .toSubscribeAtFooterOfTheCartPage(emailForSubscribing)
                .isSubscribed();
        log.info("Account has been subscribed");
    }

    public ArrayList<String> getAddedProducts() {
        log.info("Getting added products to the cart...");
        return
                cartPage
                        .getListOfAddingProducts();

    }

    public Integer getProductQuantity(String productName) {
        return
                cartPage
                        .productQuantity(productName);
    }

    public CartPageSteps clickIOnProceedToCheckoutButton() {
        basePage.clickOnButton($x(PROCEED_TO_CHECKOUT_BUTTON));
        log.info("Click on 'Proceed to checkout' button");
        return this;
    }

    public CartPageSteps clickOnRegisterLoginButton() {
        basePage.clickOnButton($x(REGISTER_LOGIN_BUTTON));
        log.info("Click on 'Register / Login' button");
        return this;
    }

    public CartPageSteps deleteItem(String productName) {
        $x(format(DELETE_BUTTON, productName)).shouldBe(Condition.visible).click();
        //waiting like sleep, because need to wait 2 seconds, while product will be deleted
        sleep(2000);
        log.info("Product has been deleted from the added product list");
        return this;
    }
}
