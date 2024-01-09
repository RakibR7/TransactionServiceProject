package atu.ie.transactionserviceproject;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class BankService {
    private final BankRepository bankRepo;


    @Autowired
    public BankService(BankRepository bankRepo) {
        this.bankRepo = bankRepo;
    }
    @Transactional
    public void createUser(Bank bank) {
        System.out.println("This got called");
        bankRepo.save(bank);
    }

    @Transactional
    public String buyProduct(PurchaseRequest purchaseRequest) {
        Optional<Bank> buyerOptional = bankRepo.findByEmail(purchaseRequest.getBuyerEmail());
        Optional<Bank> sellerOptional = bankRepo.findByEmail(purchaseRequest.getSellerEmail());

        Bank buyer = buyerOptional.orElseThrow(() -> new RuntimeException("Buyer not found"));
        Bank seller = sellerOptional.orElseThrow(() -> new RuntimeException("Seller not found"));

        if (buyer.getBalance() > purchaseRequest.getAmount()) {
            buyer.setBalance(buyer.getBalance() - purchaseRequest.getAmount());
            seller.setBalance(seller.getBalance() + purchaseRequest.getAmount());
            bankRepo.save(buyer);
            bankRepo.save(seller);
            return "Purchase completed successfully";
        } else {
            return "Purchase Failed";
        }
    }

}
