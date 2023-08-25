package pages;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$x;
import static constants.ItemPageLocators.*;
import static org.testng.Assert.assertEquals;

public class ItemPage {
    public void getItemDetailsAndCompareWithTestData(Map<String, String>... itemParams) {
        Map<String, String> actualExpectedParams = new HashMap<>();
        for (Map<String, String> paramMap : itemParams) {
            actualExpectedParams.putAll(paramMap);
        }
        for (String key : actualExpectedParams.keySet()) {
            switch (key) {
                case "name":
                    String actualName = $x(PRODUCT_NAME).getText();
                    String expectedName = actualExpectedParams.get(key);
                    assertEquals(actualName, expectedName, "The product name doesn't match...");
                    break;
                case "category":
                    String actualCategory = ($x(PRODUCT_CATEGORY).getText()).substring(10);
                    String expectedCategory = actualExpectedParams.get(key);
                    assertEquals(actualCategory, expectedCategory, "The product category doesn't match...");
                    break;
                case "price":
                    String actualPrice = ($x(PRODUCT_PRICE).getText());
                    String expectedPrice = actualExpectedParams.get(key);
                    assertEquals(actualPrice, expectedPrice, "The product price doesn't match...");
                    break;
                case "availability":
                    String actualAvailability = ($x(PRODUCT_AVAILABILITY).getText()).substring(14);
                    String expectedAvailability = actualExpectedParams.get(key);
                    assertEquals(actualAvailability, expectedAvailability, "The product availability doesn't match...");
                    break;
                case "condition":
                    String actualCondition = ($x(PRODUCT_CONDITION).getText()).substring(11);
                    String expectedCondition = actualExpectedParams.get(key);
                    assertEquals(actualCondition, expectedCondition, "The product condition doesn't match...");
                    break;
                case "brand":
                    String actualBrand = ($x(PRODUCT_BRAND).getText()).substring(7);
                    String expectedBrand = actualExpectedParams.get(key);
                    assertEquals(actualBrand, expectedBrand, "The product brand doesn't match...");
                    break;
            }
        }
    }
}
