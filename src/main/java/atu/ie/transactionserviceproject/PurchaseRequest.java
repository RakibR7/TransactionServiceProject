package atu.ie.transactionserviceproject;

import lombok.Data;


@Data
public class PurchaseRequest {
    private String email;
    private long amount;
}
