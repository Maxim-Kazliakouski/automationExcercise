package dto;

import lombok.*;

@AllArgsConstructor
@Data
@Builder
public class Account {
    // account information
    public String gender;
    public String name;
    public String email;
    public String password;
    public String dayOfBirth;
    public String monthOfBirth;
    public String yearOfBirth;
    public Object checkBox1;
    public Object checkBox2;
    // address information
    public String firstName;
    public String lastName;
    public String company;
    public String addressRequired;
    public String address2;
    public String country;
    public String state;
    public String city;
    public String zipCode;
    public String mobileNumber;
}