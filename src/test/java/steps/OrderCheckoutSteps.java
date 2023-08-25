package steps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import dto.Account;
import dto.OrderCheckout;
import factories.OrderCheckoutFactory;
import org.openqa.selenium.By;
import pages.CartPage;
import pages.OrderCheckoutPage;

import java.util.ArrayList;
import java.util.Arrays;

import static com.codeborne.selenide.Selenide.*;

public class OrderCheckoutSteps {
    OrderCheckoutPage orderCheckoutPage;

    public OrderCheckoutSteps() {
        orderCheckoutPage = new OrderCheckoutPage();
    }

    public OrderCheckout getData() {
        return
                orderCheckoutPage
                        .getDataFromCreatedAccount();
    }
}
