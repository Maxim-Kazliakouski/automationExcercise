package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class OrderCheckout {
    String gender;
    String firstName;
    String lastName;
    String company;
    String addressRequired;
    String address2;
    String city;
    String state;
    String zipCode;
    String country;
    String mobileNumber;
}
