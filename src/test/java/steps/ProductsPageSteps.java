package steps;

import dto.ProductReview;
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

    public ProductsPageSteps openProductsPage() {
        productsPage
                .openPage();
        return this;
    }

    public ProductsPageSteps isProductPageOpened() {
        productsPage
                .isOpened();
        log.info("Product page is opened");
        return this;
    }

    public ProductsPageSteps getProductsList() {
        log.info("Getting product list...");
        ProductsPage
                .getProductsList();
        return this;
    }

    public void comparingProductsList() {
        log.info("Comparing products list...");
        productsPage
                .isProductsListValid();
    }

    public void chooseItem(String itemName) {
        productsPage
                .chooseProductFromTheListByClickingViewButton(itemName);
        log.info(format("Choose product --> '%s' from the product list", itemName));
    }

    public ProductsPageSteps searchProductByName(String productName) {
        productsPage
                .searchProductByName(productName)
                .isSearchPageOpened();
        log.info(format("Searching product --> '%s'", productName));
        return this;
    }

    public void areSearchedProductsFound(String productName) {
        productsPage
                .areSearchedProductsVisible(productName);
        log.info(format("Searching product --> '%s' has been found", productName));
    }

    public ProductsPageSteps addProduct(String productName) {
        log.info(format("Adding product '%s' to the cart...", productName));
        productsPage
                .addProduct(productName);
        return this;
    }

    public ProductsPageSteps clickOnContinueShoppingButton() {
        log.info("Click on 'Continue shopping' button");
        productsPage
                .continuingShoppingFromModalWindowAfterAddingProduct();
        return this;
    }

    public ProductsPageSteps clickOnViewCartButton() {
        log.info("Click on 'View cart' button");
        productsPage
                .goingToCartFromModalWindowAfterAddingProduct();
        log.info("Redirecting to the cart page...");
        return this;
    }

    public ProductsPageSteps clickOnViewProductButton(String itemName) {
        productsPage
                .chooseProductFromTheListByClickingViewButton(itemName);
        log.info(format("Choose product '%s' from the list by clicking on view button", itemName));
        return this;
    }

    public void createProductReview(ProductReview productReview) {
        productsPage
                .createReview(productReview);
        log.info("Product review has been created");
        basePage
                .clickOnButton(SUBMIT_BUTTON);
        log.info("Click on 'Submit' button");
    }

    public String checkSuccessCreateReviewMessage() {
        log.info("Success created review message has been created");
        return productsPage.gettingSuccessMessage();
    }
}
