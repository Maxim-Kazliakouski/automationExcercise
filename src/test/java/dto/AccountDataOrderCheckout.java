package dto;

import lombok.*;

@AllArgsConstructor
@Data
@Builder
public class AccountDataOrderCheckout {
    public String gender;
    // address information
    public String firstName;
    public String lastName;
    public String company;
    public String addressRequired;
    public String address2;
    public String state;
    public String city;
    public String zipCode;
    public String country;
    public String mobileNumber;
}