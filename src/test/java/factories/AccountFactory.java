package factories;

import com.github.javafaker.Faker;
import dto.Account;
import dto.OrderCheckout;

public class AccountFactory {
    static Faker faker = new Faker();
    static String name = faker.name().name();
    static String email = faker.internet().emailAddress();
    static String password = faker.code().gtin8();
    static String dayOfBirth = String.valueOf(faker.number().numberBetween(1, 28));
    static String monthOfBirth = String.valueOf(faker.number().numberBetween(1, 12));
    static String yearOfBirth = String.valueOf(faker.number().numberBetween(1986, 2010));
    static String firstName = faker.name().firstName();
    static String lastName = faker.name().lastName();
    static String company = faker.company().name();
    static String addressRequired = faker.address().fullAddress();
    static String address2 = faker.address().secondaryAddress();
    static String city = "Washington";
    static String state = "Neapolitans";
    static String zipCode = faker.address().zipCode();
    static String mobileNumber = faker.phoneNumber().cellPhone();

    public static Account getAccount(
            String title,
            String country
    ) {
        return Account.builder()
                .gender(title)
                .name(name)
                .email(email)
                .password(password)
                .dayOfBirth(dayOfBirth)
                .monthOfBirth(monthOfBirth)
                .yearOfBirth(yearOfBirth)
                .firstName(firstName)
                .lastName(lastName)
                .company(company)
                .addressRequired(addressRequired)
                .address2(address2)
                .country(country)
                .city(city)
                .state(state)
                .zipCode(zipCode)
                .mobileNumber(mobileNumber)
                .build();
    }

    public static OrderCheckout getDataAccountForOrderCheckoutPage(String title, String country) {
        return OrderCheckout.builder()
                .gender(title)
                .firstName(firstName)
                .lastName(lastName)
                .company(company)
                .addressRequired(addressRequired)
                .address2(address2)
                .state(state)
                .city(city)
                .zipCode(zipCode)
                .country(country)
                .mobileNumber(mobileNumber)
                .build();
    }
}