package steps;

import lombok.extern.log4j.Log4j2;
import pages.ProductDetailsPage;

import static java.lang.String.format;

@Log4j2
public class ProductsDetailsPageSteps {
    ProductDetailsPage productDetailsPage;

    public ProductsDetailsPageSteps() {
        productDetailsPage = new ProductDetailsPage();
    }

    public ProductsDetailsPageSteps isProductDetailsPageOpened() {
        productDetailsPage
                .isOpened();
        log.info("Product detail page is opened");
        return this;
    }

    public ProductsDetailsPageSteps increaseProductQuantity(Integer quantity) {
        productDetailsPage
                .increaseQuantity(quantity);
        log.info(format("The product quantity has been increased to --> %s", quantity));
        return this;
    }

    public ProductsDetailsPageSteps addToCart() {
        productDetailsPage
                .clickOnAddToCartButton();
        log.info("Click on add to cart button");
        return this;
    }

    public void viewCart() {
        productDetailsPage
                .clickOnViewCartButton();
        log.info("Click on view cart button");
    }
}
