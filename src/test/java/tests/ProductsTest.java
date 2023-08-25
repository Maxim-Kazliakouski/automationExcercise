package tests;

import dto.Account;
import dto.AccountDataOrderCheckout;
import dto.OrderCheckout;
import factories.AccountFactory;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.sleep;
import static org.testng.Assert.assertEquals;

public class ProductsTest extends BaseTest {
    @Test
    @Description("Test for checking all products")
    public void productList() {
        productsPageSteps
                .openProductsPage()
                .getProductsList()
                .comparingProductsList();
    }

    @Test
    @Description("Test for checking product details page")
    public void productDetailsPage() {
        Map<String, String> testData = Map.ofEntries(
                Map.entry("name", "Fancy Green Top"),
                Map.entry("category", "Women > Tops"),
                Map.entry("price", "Rs. 700"),
                Map.entry("availability", "In Stock"),
                Map.entry("condition", "New"),
                Map.entry("brand", "Polo")
        );
        productsPageSteps
                .openProductsPage()
                .chooseItem(testData.get("name"));
        itemPageSteps
                .checkingItemDetails(testData);
    }

    @Test
    @Description("Test for searching product")
    public void searchProduct() {
        String productName = "Men";
        productsPageSteps
                .openProductsPage()
                .searchProductByName(productName)
                .areSearchedProductsFound(productName);
    }

    @Test
    @Description("Test for adding products in cart")
    public void addingProductToCart() {
        Map<String, String> testData = Map.ofEntries(
                Map.entry("product1", "Men Tshirt"),
                Map.entry("product2", "Stylish Dress"));
        productsPageSteps
                .openProductsPage()
                .addProduct(testData.get("product1"))
                .clickOnContinueShoppingButton()
                .addProduct(testData.get("product2"))
                .clickOnViewCartButton();
        cartPageSteps
                .isCartPageOpened();
        assertEquals(cartPageSteps.getAddedProducts(), testData.values());
    }

    @Test
    @Description("Test for verifying product quantity in Cart")
    public void verifyingProductQuantityInCart() {
        int productQuantity = 5;
        Map<String, String> testData = Map.ofEntries(
                Map.entry("product1", "Men Tshirt"));
        productsPageSteps
                .openProductsPage()
                .clickOnViewProductButton(testData.get("product1"));
        productsDetailsPageSteps
                .isProductDetailsPageOpened()
                .increaseProductQuantity(productQuantity)
                .addToCart()
                .viewCart();
        cartPageSteps
                .isCartPageOpened()
                .getAddedProducts();
        assertEquals(cartPageSteps.getAddedProducts(), (testData.values()));
        assertEquals(cartPageSteps.getProductQuantity(testData.get("product1")), productQuantity);
    }

    @Test
    @Description("Place Order: Register while Checkout")
    public void registerAfterOrder() {
        Account account = AccountFactory.getAccount("Mrs", "Canada");
        OrderCheckout dataAccount = AccountFactory.getDataAccountForOrderCheckoutPage("Mrs", "Canada");
        Map<String, String> testData = Map.ofEntries(
                Map.entry("product1", "Men Tshirt"),
                Map.entry("product2", "Stylish Dress"));
        mainPageSteps
                .isMainPageOpened()
                .clickOnTab("Products");
        productsPageSteps
                .isProductPageOpened()
                .addProduct(testData.get("product1"))
                .clickOnContinueShoppingButton()
                .addProduct(testData.get("product2"))
                .clickOnViewCartButton();
        cartPageSteps
                .isCartPageOpened()
                .clickIOnProceedToCheckoutButton()
                .clickOnRegisterLoginButton();
        signUpPageSteps
                .createNewUser(account)
                .isAccountCreated()
                .isLoggedAsCreatedAccount();
        mainPageSteps
                .clickOnTab("Cart");
        cartPageSteps
                .clickIOnProceedToCheckoutButton();
        assertEquals(orderCheckoutSteps.getData(), dataAccount);
    }
}
