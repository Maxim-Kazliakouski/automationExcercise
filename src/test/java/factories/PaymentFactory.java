package factories;

import com.github.javafaker.Faker;
import dto.Payment;

public class PaymentFactory {
    static Faker faker = new Faker();

    public static Payment getPayment() {
        return Payment.builder()
                .nameOnCard(faker.name().firstName() + " " + faker.name().lastName())
                .cardNumber(faker.number().digits(16))
                .cvc(faker.number().digits(3))
                .expiration(faker.number().numberBetween(1, 2))
                .year(faker.number().numberBetween(1900, 2010))
                .build();
    }
}