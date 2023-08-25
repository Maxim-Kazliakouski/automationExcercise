package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;
import static constants.CartPageLocators.LIST_OF_ADDED_PRODUCTS;
import static constants.CartPageLocators.PRODUCT_QUANTITY;
import static constants.MainPageLocators.*;
import static java.lang.String.format;

@Log4j2
public class CartPage extends BasePage {

    public CartPage openPage() {
        open("/view_cart");
        return this;
    }

    public CartPage isOpened() {
        $x(CART_PAGE_MARKER).shouldBe(Condition.visible);
        return this;
    }

    public CartPage toSubscribeAtFooterOfTheCartPage(String email) {
        scrollToTheEndOfAPage();
        $x(SUBSCRIPTION).shouldBe(Condition.visible);
        $(SUBSCRIPTION_EMAIL_ADDRESS_FIELD).setValue(email);
        $(SUBSCRIPTION_SUBMIT_BUTTON).click();
        return this;
    }

    public void isSubscribed() {
        $x(SUCCESS_SUBSCRIBE_NOTIFICATION).shouldBe(Condition.visible);
    }

    public ArrayList<String> getListOfAddingProducts() {
        ElementsCollection addingProducts = $$x(LIST_OF_ADDED_PRODUCTS);
        ArrayList<String> addedProducts = new ArrayList<>();
        log.info("The list off adding products:");
        for (SelenideElement eachAddingProduct : addingProducts) {
            log.info(eachAddingProduct.getText());
            addedProducts.add(eachAddingProduct.getText());
        }
        return addedProducts;
    }

    public Integer productQuantity(String productName) {
        String productQuantityString = $x(format(PRODUCT_QUANTITY, productName)).shouldBe(Condition.visible).getText();
        return Integer.valueOf(productQuantityString);
    }
}
