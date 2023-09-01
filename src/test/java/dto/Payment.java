package dto;

import lombok.*;

@AllArgsConstructor
@Data
@Builder
public class Payment {
    String nameOnCard;
    String cardNumber;
    String cvc;
    Integer expiration;
    Integer year;
}