package pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static constants.MainPageConstants.PRODUCTS_PAGE_MARKER;

public class ProductPage {

    public ProductPage openPage() {
        open("/products");
        return this;
    }

    public void isOpened() {
        $x(PRODUCTS_PAGE_MARKER).shouldBe(Condition.visible);
    }
}
