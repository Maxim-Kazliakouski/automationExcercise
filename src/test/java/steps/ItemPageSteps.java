package steps;

import pages.ItemPage;

import java.util.Map;

public class ItemPageSteps {
    ItemPage itemPage;

    public ItemPageSteps() {
        itemPage = new ItemPage();
    }

    public void checkingItemDetails(Map<String, String>... params) {
        itemPage
                .getItemDetailsAndCompareWithTestData(params);
    }
}