package tests;

import dto.Account;
import dto.OrderCheckout;
import dto.Payment;
import factories.AccountFactory;
import factories.PaymentFactory;
import io.qameta.allure.TmsLink;
import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;

@Log4j2
public class CartPageTest extends BaseTest {

    @TmsLink("case=23")
    @Test
    @Description("Test for checking subscription at cart page")
    public void subscriptionAtCartPage() {
        mainPageSteps
                .openPage()
                .clickOnTab("Cart");
        cartPageSteps
                .isCartPageOpened()
                .toSubscribe("111@mail.co");
    }

    @TmsLink("case=24")
    @Test
    @Description("Remove Products From Cart")
    public void removeProductsFromCart() {
        Map<String, String> testData = Map.ofEntries(
                Map.entry("product1", "Men Tshirt"),
                Map.entry("product2", "Stylish Dress"));
        mainPageSteps
                .openPage()
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
                .deleteItem(testData.get("product1"));
        assertEquals(cartPageSteps.getAddedProducts().get(0), testData.get("product2"));
    }

    @TmsLink("case=25")
    @Test
    @Description("Add products to cart from Recommended items")
    public void addRecommendedItems() {
        List<String> recommendedItems = new ArrayList<>(List.of("Winter Top"));
        mainPageSteps
                .openPage()
                .isMainPageOpened()
                .scrollToTheRecommendedItemsHeader()
                .isRecommendedItemsIsVisible()
                .chooseRecommendedItem(recommendedItems.get(0));
        productsPageSteps
                .clickOnViewCartButton();
        cartPageSteps
                .isCartPageOpened();
        assertEquals(cartPageSteps.getAddedProducts(), recommendedItems, "The recommended items in the cart doesn't match!");
    }

    @TmsLink("case=26")
    @Test
    @Description("Download Invoice after purchase order")
    public void downloadInvoiceAfterPurchaseOrder() throws FileNotFoundException {
        Account account = AccountFactory.getAccount("Mrs", "Canada");
        OrderCheckout dataAccount = AccountFactory.getDataAccountForOrderCheckoutPage("Mrs", "Canada");
        Payment payment = PaymentFactory.getPayment();
        String expectedInvoiceData = format("Hi %s %s, Your total purchase amount is 1900. Thank you", dataAccount.getFirstName(), dataAccount.getLastName());
        Map<String, String> testData = Map.ofEntries(
                Map.entry("product1", "Men Tshirt"),
                Map.entry("product2", "Stylish Dress"));
        mainPageSteps
                .openPage()
                .isMainPageOpened();
        productsPageSteps
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
        orderCheckoutSteps
                .enterComment("Test")
                .clickOnPlaceOrderButton();
        paymentPageSteps
                .isPaymentPageIsOpened()
                .createNewPayment(payment)
                .gettingSuccessfulMessage();
        assertEquals(paymentPageSteps.gettingInvoice(), expectedInvoiceData,
                "The data in voice doesn't match with expected data");
        paymentPageSteps
                .clickOnContinueButton();
        signUpPageSteps
                .deleteAccount();
    }
}