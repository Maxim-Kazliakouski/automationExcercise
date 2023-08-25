package steps;

import pages.ProductDetailsPage;

public class ProductsDetailsPageSteps {
    ProductDetailsPage productDetailsPage;

    public ProductsDetailsPageSteps() {
        productDetailsPage = new ProductDetailsPage();
    }

    public ProductsDetailsPageSteps isProductDetailsPageOpened() {
        productDetailsPage
                .isOpened();
        return this;
    }

    public ProductsDetailsPageSteps increaseProductQuantity(Integer quantity) {
        productDetailsPage
                .increaseQuantity(quantity);
        return this;
    }

    public ProductsDetailsPageSteps addToCart() {
        productDetailsPage
                .clickOnAddToCartButton();
        return this;
    }

    public void viewCart() {
        productDetailsPage
                .clickOnViewCartButton();
    }
}
