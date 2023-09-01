package tests;

import dto.Account;
import dto.OrderCheckout;
import dto.Payment;
import dto.ProductReview;
import factories.AccountFactory;
import factories.PaymentFactory;
import factories.ProductReviewFactory;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import pages.ProductsPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    @Description("Test for adding products to cart")
    public void addingProductToCart() {
        Map<String, String> testData = Map.ofEntries(
                Map.entry("product1", "Men Tshirt"),
                Map.entry("product2", "Stylish Dress"));
        productsPageSteps
                .openProductsPage()
                .isProductPageOpened()
                .addProduct(testData.get("product1"))
                .clickOnContinueShoppingButton()
                .addProduct(testData.get("product2"))
                .clickOnViewCartButton();
        cartPageSteps
                .isCartPageOpened();
        assertEquals(cartPageSteps.getAddedProducts(), new ArrayList<>(testData.values()));
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
        signUpPageSteps
                .deleteAccount();
    }

    @Test
    @Description("Place Order: Register before Checkout")
    public void registerBeforeOrder() {
        Account account = AccountFactory.getAccount("Mrs", "Canada");
        Map<String, String> testData = Map.ofEntries(
                Map.entry("product1", "Men Tshirt"),
                Map.entry("product2", "Stylish Dress"));
        Payment payment = PaymentFactory.getPayment();
        mainPageSteps
                .isMainPageOpened()
                .clickOnTab("Signup / Login");
        signUpPageSteps
                .isSignUpLoginPageOpened()
                .createNewUser(account)
                .isLoggedAsCreatedAccount();
        productsPageSteps
                .isProductPageOpened()
                .addProduct(testData.get("product1"))
                .clickOnContinueShoppingButton()
                .addProduct(testData.get("product2"))
                .clickOnViewCartButton();
        cartPageSteps
                .isCartPageOpened()
                .clickIOnProceedToCheckoutButton();
        orderCheckoutSteps
                .enterComment("test")
                .clickOnPlaceOrderButton();
        paymentPageSteps
                .isPaymentPageIsOpened()
                .createNewPayment(payment);
        assertEquals(paymentPageSteps.gettingSuccessfulMessage(), "Congratulations! Your order has been confirmed!");
        signUpPageSteps
                .deleteAccount();
    }

    @Test
    @Description("Place Order: Login before Checkout")
    public void loginBeforeCheckOut() {
        Payment payment = PaymentFactory.getPayment();
        Map<String, String> testData = Map.ofEntries(
                Map.entry("product1", "Men Tshirt"),
                Map.entry("product2", "Stylish Dress"));
        String email = "123@mail.com";
        String password = "123";
        String name = "max";
        mainPageSteps
                .isMainPageOpened()
                .clickOnTab("Signup / Login");
        loginPageSteps
                .loginWithCreds(email, password);
        signUpPageSteps
                .isLoggedAccount(name);
        productsPageSteps
                .isProductPageOpened()
                .addProduct(testData.get("product1"))
                .clickOnContinueShoppingButton()
                .addProduct(testData.get("product2"))
                .clickOnViewCartButton();
        cartPageSteps
                .isCartPageOpened()
                .clickIOnProceedToCheckoutButton();
        orderCheckoutSteps
                .enterComment("test")
                .clickOnPlaceOrderButton();
        paymentPageSteps
                .isPaymentPageIsOpened()
                .createNewPayment(payment);
        assertEquals(paymentPageSteps.gettingSuccessfulMessage(), "Congratulations! Your order has been confirmed!");
    }

    @Test
    @Description("Search Products and Verify Cart After Login")
    public void searchAndVerifyProducts() {
        String searchingNameProduct = "Pure Cotton";
        List<String> expectedProducts = new ArrayList<>(Arrays.asList("Pure Cotton V-Neck T-Shirt", "Pure Cotton Neon Green Tshirt"));
        productsPageSteps
                .openProductsPage()
                .isProductPageOpened()
                .searchProductByName(searchingNameProduct);
        assertEquals(ProductsPage.getProductsList(), expectedProducts);
    }

    @Test
    @Description("Add review on product")
    public void addProductReview() {
        ProductReview productReview = ProductReviewFactory.getReview("Test text for review");
        productsPageSteps
                .openProductsPage()
                .isProductPageOpened()
                .clickOnViewProductButton("Stylish Dress")
                .createProductReview(productReview);
        assertEquals(productsPageSteps.checkSuccessCreateReviewMessage(),
                "Thank you for your review.", "The product review hasn't been created!");
    }
}