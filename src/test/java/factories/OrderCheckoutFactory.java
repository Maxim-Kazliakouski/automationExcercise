package factories;

import dto.OrderCheckout;

public class OrderCheckoutFactory {
    public static OrderCheckout setData() {
        return OrderCheckout.builder()
                .gender("")
                .firstName("")
                .lastName("")
                .company("")
                .addressRequired("")
                .address2("")
                .city("")
                .state("")
                .zipCode("")
                .country("")
                .mobileNumber("")
                .build();
    }
}
