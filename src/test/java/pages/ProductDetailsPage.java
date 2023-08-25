package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import tests.wrappers.TextInputLabel;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static constants.MainPageLocators.PRODUCT_DETAILS_PAGE_MARKER;
import static constants.ProductDetailsPageLocators.ADD_TO_CART;
import static constants.ProductsPageLocators.VIEW_CART_BUTTON;

public class ProductDetailsPage extends BasePage{

    public ProductDetailsPage isOpened() {
    $x(PRODUCT_DETAILS_PAGE_MARKER).shouldBe(Condition.visible);
    return this;
    }

    public void increaseQuantity(Integer quantity) {
        new TextInputLabel("Quantity").write(String.valueOf(quantity));
    }

    public void clickOnAddToCartButton() {
        SelenideElement button = $x(ADD_TO_CART).shouldBe(Condition.visible);
        button.click();
    }

    public void clickOnViewCartButton() {
        $x(VIEW_CART_BUTTON).shouldBe(Condition.visible).click();
    }
}
