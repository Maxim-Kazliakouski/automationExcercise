package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import pages.ItemPage;

import java.util.Map;

@Log4j2
public class ItemPageSteps {
    ItemPage itemPage;

    public ItemPageSteps() {
        itemPage = new ItemPage();
    }

    @Step("Comparing product details with test data")
    public void checkingItemDetails(Map<String, String>... params) {
        log.info("Comparing product details with test data...");
        itemPage
                .getItemDetailsAndCompareWithTestData(params);
    }
}