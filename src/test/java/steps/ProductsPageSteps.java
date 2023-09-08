package steps;

import dto.ProductReview;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import pages.BasePage;
import pages.ProductsPage;

import static constants.ProductsPageLocators.SUBMIT_BUTTON;
import static java.lang.String.format;

@Log4j2
public class ProductsPageSteps {
    ProductsPage productsPage;
    BasePage basePage;

    public ProductsPageSteps() {
        productsPage = new ProductsPage();
        basePage = new BasePage();
    }

    @Step("Open product page")
    public ProductsPageSteps openProductsPage() {
        productsPage
                .openPage();
        return this;
    }

    @Step("Is product page opened")
    public ProductsPageSteps isProductPageOpened() {
        productsPage
                .isOpened();
        log.info("Product page is opened");
        return this;
    }

    @Step("Getting product list")
    public ProductsPageSteps getProductsList() {
        log.info("Getting product list...");
        ProductsPage
                .getProductsList();
        return this;
    }

    @Step("Comparing products list")
    public void comparingProductsList() {
        log.info("Comparing products list...");
        productsPage
                .isProductsListValid();
    }

    @Step("Choose product --> '{itemName}' from the product list")
    public void chooseItem(String itemName) {
        productsPage
                .chooseProductFromTheListByClickingViewButton(itemName);
        log.info(format("Choose product --> '%s' from the product list", itemName));
    }

    @Step("Searching product --> '{productName}'")
    public ProductsPageSteps searchProductByName(String productName) {
        productsPage
                .searchProductByName(productName)
                .isSearchPageOpened();
        log.info(format("Searching product --> '%s'", productName));
        return this;
    }

    @Step("Is searched product '{productName} has been found")
    public void areSearchedProductsFound(String productName) {
        productsPage
                .areSearchedProductsVisible(productName);
        log.info(format("Searching product --> '%s' has been found", productName));
    }

    @Step("Adding product '{productName}' to the cart")
    public ProductsPageSteps addProduct(String productName) {
        log.info(format("Adding product '%s' to the cart...", productName));
        productsPage
                .addProduct(productName);
        return this;
    }

    @Step("Click on 'Continue shopping' button")
    public ProductsPageSteps clickOnContinueShoppingButton() {
        log.info("Click on 'Continue shopping' button");
        productsPage
                .continuingShoppingFromModalWindowAfterAddingProduct();
        return this;
    }

    @Step("Click on 'View cart' button")
    public ProductsPageSteps clickOnViewCartButton() {
        log.info("Click on 'View cart' button");
        productsPage
                .goingToCartFromModalWindowAfterAddingProduct();
        log.info("Redirecting to the cart page...");
        return this;
    }

    @Step("Choose product '{itemName}' from the list by clicking on view button")
    public ProductsPageSteps clickOnViewProductButton(String itemName) {
        productsPage
                .chooseProductFromTheListByClickingViewButton(itemName);
        log.info(format("Choose product '%s' from the list by clicking on view button", itemName));
        return this;
    }

    @Step("Creating product review")
    public void createProductReview(ProductReview productReview) {
        productsPage
                .createReview(productReview);
        log.info("Product review has been created");
        basePage
                .clickOnButton(SUBMIT_BUTTON);
        log.info("Click on 'Submit' button");
    }

    @Step("Check success created review message")
    public String checkSuccessCreateReviewMessage() {
        log.info("Success created review message has been created");
        return productsPage.gettingSuccessMessage();
    }
}