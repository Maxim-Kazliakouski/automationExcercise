package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import dto.ProductReview;
import lombok.extern.log4j.Log4j2;
import tests.wrappers.TextInputOther;

import java.util.ArrayList;
import java.util.Collections;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static constants.MainPageLocators.PRODUCTS_PAGE_MARKER;
import static constants.ProductsPageLocators.*;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class ProductsPage extends BasePage {

    public ProductsPage openPage() {
        open("/products");
        return this;
    }

    public void isOpened() {
        $x(PRODUCTS_PAGE_MARKER).shouldBe(Condition.visible);
    }

    public static ArrayList<String> getProductsList() {
        ElementsCollection products = $$x(PRODUCTS_LIST);
        ArrayList<String> productsList = new ArrayList<>();
        for (SelenideElement productName : products) {
            productsList.add(productName.getText());
        }
        return productsList;
    }

    public void isProductsListValid() {
        ArrayList<String> expectedList = gettingListFromTxtFile();
        Collections.sort(expectedList);
        ArrayList<String> actualList = getProductsList();
        Collections.sort(actualList);
        assertEquals(actualList, expectedList, "The lists aren't equal");
    }

    public void chooseProductFromTheListByClickingViewButton(String itemName) {
        $x(format(VIEW_PRODUCT_BUTTON, itemName)).click();
    }

    public ProductsPage searchProductByName(String productName) {
        $(PRODUCT_SEARCH_FIELD).setValue(productName);
        $(SEARCH_BUTTON).click();
        return this;
    }

    public ProductsPage isSearchPageOpened() {
        $x(SEARCH_PAGE_MARKER).shouldBe(Condition.visible);
        return this;
    }

    public void areSearchedProductsVisible(String productName) {
        ElementsCollection listOfSearchedProducts = $$x("//div[@class='productinfo text-center']//p");
        ArrayList<String> foundProducts = new ArrayList<>();
        log.info("The products names: ");
        for (SelenideElement products : listOfSearchedProducts) {
            log.info(products.getText());
            foundProducts.add(products.getText());
        }
        log.info("The list of found products --> " + foundProducts);
        for (String names : foundProducts) {
            assertTrue((names.toLowerCase()).contains(productName.toLowerCase()));
        }
        log.info(format("The product name '%s' is in list '%s", productName, foundProducts));
    }

    public ProductsPage addProduct(String productName) {
        $x(format(PRODUCT_VIEW_BUTTON, productName)).scrollIntoView(false);
        $x(format(PRODUCT_NAME, productName)).hover();
        SelenideElement addToCartButton = $x(format(ADD_TO_CART, productName)).shouldBe(visible, durationInSeconds("default_timeout_in_seconds"));
        jsClick(addToCartButton);
        return this;
    }

    public ProductsPage goingToCartFromModalWindowAfterAddingProduct() {
        $x(VIEW_CART_BUTTON).shouldBe(visible, durationInSeconds("default_timeout_in_seconds")).click();
        return this;
    }

    public ProductsPage continuingShoppingFromModalWindowAfterAddingProduct() {
        $x(CONTINUE_SHOPPING_BUTTON).shouldBe(visible, durationInSeconds("default_timeout_in_seconds")).click();
        return this;
    }

    public ProductsPage createReview(ProductReview productReview) {
        new TextInputOther("input", "id", "name").write(productReview.getName());
        new TextInputOther("input", "id", "email").write(productReview.getEmail());
        new TextInputOther("textarea", "name", "review").write(productReview.getTextReview());
        return this;
    }

    public String gettingSuccessMessage() {
        return $x(SUCCESS_CREATING_REVIEW_MESSAGE).shouldBe(visible).getText();
    }
}
