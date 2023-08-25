package tests;

import jdk.jfr.Description;
import org.testng.annotations.Test;

public class CartPageTest extends BaseTest {

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
}
