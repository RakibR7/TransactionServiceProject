package atu.ie.transactionserviceproject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionNumber;

    @NotBlank(message = "buyer email cannot be blank")
    @Email(message = "Invalid email address")
    private String buyerEmail;

    @NotBlank(message = "Seller email cannot be blank")
    @Email(message = "Invalid email address")
    private String sellerEmail;

    private String productTitle;

    private String adCreateTime;

    @PositiveOrZero
    private float amount;

    private String time;
}
