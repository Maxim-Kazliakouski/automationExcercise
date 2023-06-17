package factories;

import com.github.javafaker.Faker;
import dto.Account;

public class AccountFactory {
    static Faker faker = new Faker();

    public static Account getAccount(
            String title,
            String dayOfBirth,
            String monthOfBirth,
            String yearOfBirth,
            String country
    ) {
        return Account.builder()
                .title(title)
                .name(faker.name().name())
                .email(faker.superhero().name() + "@mail.ru")
                .password(faker.code().gtin8())
                .dayOfBirth(dayOfBirth)
                .monthOfBirth(monthOfBirth)
                .yearOfBirth(yearOfBirth)
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .company(faker.company().name())
                .addressRequired(faker.address().fullAddress())
                .address2(faker.address().secondaryAddress())
                .country(country)
                .city(faker.address().city())
                .state(faker.address().state())
                .zipCode(faker.address().zipCode())
                .mobileNumber(faker.phoneNumber().cellPhone())
                .build();
    }
}