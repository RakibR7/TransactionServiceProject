package atu.ie.transactionserviceproject;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private final BankService bankService;

    @Autowired
    public TransactionController(BankService bankService) {
        this.bankService = bankService;
    }

    @Transactional
    @PostMapping("/initfunds")
    public ResponseEntity<String> initfunds(@RequestBody Bank bank) {

        //Inits the banks funds for new users
        System.out.println(bank);

        bankService.createUser(bank);

        return new ResponseEntity<>("BankFunds successfully Added", HttpStatus.CREATED);
    }



    @PostMapping("/buynow")
    public ResponseEntity<String> buyNow(@RequestBody PurchaseRequest purchaseRequest) {
        String result = bankService.buyProduct(purchaseRequest);


        if ("Purchase completed successfully".equals(result)) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errorMessage = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append("\n"));

        return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
    }
}