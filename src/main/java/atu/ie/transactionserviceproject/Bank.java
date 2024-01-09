package atu.ie.transactionserviceproject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long BankId;

    @Email(message = "Invalid email address")
    private String email;

    private float balance;
}
