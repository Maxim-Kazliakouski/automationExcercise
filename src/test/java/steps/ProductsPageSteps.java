package steps;

import pages.ProductsPage;

import static com.codeborne.selenide.Selenide.sleep;
import static java.lang.String.format;

public class ProductsPageSteps {
    ProductsPage productsPage;

    public ProductsPageSteps() {
        productsPage = new ProductsPage();
    }

    public ProductsPageSteps openProductsPage() {
        productsPage
                .openPage();
        return this;
    }

    public ProductsPageSteps isProductPageOpened() {
        productsPage
                .isOpened();
        return this;
    }

    public ProductsPageSteps getProductsList() {
        productsPage
                .getProductsList();
        return this;
    }

    public void comparingProductsList() {
        productsPage
                .isProductsListValid();
    }

    public void chooseItem(String itemName) {
        productsPage
                .chooseProductFromTheListByClickingViewButton(itemName);
    }

    public ProductsPageSteps searchProductByName(String productName) {
        productsPage
                .searchProductByName(productName)
                .isSearchPageOpened();
        return this;
    }

    public void areSearchedProductsFound(String productName) {
        productsPage
                .areSearchedProductsVisible(productName);
    }

    public ProductsPageSteps addProduct(String productName) {
        productsPage
                .addProduct(productName);
        return this;
    }

    public ProductsPageSteps clickOnContinueShoppingButton() {
        productsPage
                .continuingShoppingFromModalWindowAfterAddingProduct();
        return this;
    }

    public ProductsPageSteps clickOnViewCartButton() {
        productsPage
                .goingToCartFromModalWindowAfterAddingProduct();
        return this;
    }

    public void clickOnViewProductButton(String itemName) {
        productsPage
                .chooseProductFromTheListByClickingViewButton(itemName);
    }
}
