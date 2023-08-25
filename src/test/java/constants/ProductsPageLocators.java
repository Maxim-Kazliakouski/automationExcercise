package constants;

public class ProductsPageLocators {
    public static final String PRODUCTS_LIST = "//div[@class='productinfo text-center']//p";
    public static final String VIEW_PRODUCT_BUTTON = "//p[text()='%s']//..//..//..//a[text()='View Product']";
    public static final String PRODUCT_SEARCH_FIELD = "#search_product";
    public static final String SEARCH_BUTTON = "#submit_search";
    public static final String SEARCH_PAGE_MARKER = "//h2[text()='Searched Products']";
    public static final String PRODUCT_NAME = "//div[@class='productinfo text-center']//p[text()='%s']";
    public static final String ADD_TO_CART = "//div[@class='productinfo text-center']//p[text()='%s']//..//a";
    public static final String VIEW_CART_BUTTON = "//u[text()='View Cart']//ancestor::a";
    public static final String PRODUCT_VIEW_BUTTON = "//div[@class='productinfo text-center']//p[text()='%s']//..//..//..//ul//li//a";
    public static final String CONTINUE_SHOPPING_BUTTON = "//button[text()='Continue Shopping']";
}
