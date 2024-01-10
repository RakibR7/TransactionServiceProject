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
        bankRepo.save(bank);
    }


    public String buyProduct(PurchaseRequest purchaseRequest) {
        System.out.println(purchaseRequest.getBuyerEmail());
        System.out.println(purchaseRequest.getSellerEmail());

        String sellerEmail = purchaseRequest.getSellerEmail();
        String buyerEmail = purchaseRequest.getBuyerEmail();


        Optional<Bank> buyerOptional = bankRepo.findByEmail(buyerEmail);
        Optional<Bank> sellerOptional = bankRepo.findByEmail(sellerEmail);

        System.out.println("B:" + buyerOptional);
        System.out.println("S:" + sellerOptional);

        Bank buyer = buyerOptional.orElseThrow(() -> new RuntimeException("Buyer not found"));
        Bank seller = sellerOptional.orElseThrow(() -> new RuntimeException("Seller not found"));

        if (buyer.getBalance() > purchaseRequest.getAmount()) {
            buyer.setBalance(buyer.getBalance() - purchaseRequest.getAmount());
            seller.setBalance(seller.getBalance() + purchaseRequest.getAmount());
            bankRepo.save(buyer);
            bankRepo.save(seller);
            return "Purchase completed successfully";
        } else {
            return "Purchase Failed not enough funds";
        }
    }

}
