package atu.ie.transactionserviceproject;

import lombok.Data;

@Data
public class PurchaseRequest {
    private Long personId;
    private long amount;
}
