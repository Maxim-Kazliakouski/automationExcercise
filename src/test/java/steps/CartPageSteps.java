package steps;

import lombok.extern.log4j.Log4j2;
import pages.BasePage;
import pages.CartPage;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static constants.CartPageLocators.PROCEED_TO_CHECKOUT_BUTTON;
import static constants.CartPageLocators.REGISTER_LOGIN_BUTTON;

@Log4j2
public class CartPageSteps {
    CartPage cartPage;
    BasePage basePage;

    public CartPageSteps() {
        cartPage = new CartPage();
        basePage = new BasePage();
    }

    public CartPageSteps openCartPage() {
        open("/view_cart");
        return this;
    }

    public CartPageSteps isCartPageOpened() {
        cartPage
                .isOpened();
        return this;
    }

    public void toSubscribe(String emailForSubscribing) {
        cartPage
                .toSubscribeAtFooterOfTheCartPage(emailForSubscribing)
                .isSubscribed();
    }

    public ArrayList<String> getAddedProducts() {
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
        return this;
    }

    public CartPageSteps clickOnRegisterLoginButton() {
        basePage.clickOnButton($x(REGISTER_LOGIN_BUTTON));
        return this;
    }
}
