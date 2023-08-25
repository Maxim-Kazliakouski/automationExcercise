package constants;

public class ItemPageLocators {
    public static final String PRODUCT_NAME = "//div[@class='product-information']//h2";
    public static final String PRODUCT_CATEGORY = "//p[contains(text(),'Category')]";
    public static final String PRODUCT_PRICE = "//span[contains(text(),'Rs.')]";
    public static final String PRODUCT_AVAILABILITY = "//b[text()='Availability:']//ancestor::p";
    public static final String PRODUCT_CONDITION = "//b[text()='Condition:']//ancestor::p";
    public static final String PRODUCT_BRAND = "//b[text()='Brand:']//ancestor::p";
}
