package constants;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class CheckOutPageLocators {
    public static final String LIST_OF_ADDRESS_DETAILS = "//ul[@id='address_delivery']//li";
    public static final SelenideElement PLACE_ORDER_BUTTON = $x("//a[contains(@class,'check_out')]");
}
