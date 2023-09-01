package factories;

import com.github.javafaker.Faker;
import dto.ProductReview;

public class ProductReviewFactory {
    static Faker faker = new Faker();

    public static ProductReview getReview(String textReview) {
        return ProductReview.builder()
                .name(faker.name().firstName())
                .email(faker.internet().emailAddress())
                .textReview(textReview)
                .build();
    }
}