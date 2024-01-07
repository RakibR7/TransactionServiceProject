package atu.ie.transactionserviceproject;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/person")
@RestController
public class PersonController {
    private final PersonService personService;
    public PersonController(PersonService personService) { this.personService = personService; }
    @PostMapping("/buynow")
    public ResponseEntity<String> buyNow(@RequestBody PurchaseRequest purchaseRequest) {
        String result = personService.processPurchase(purchaseRequest.getEmail(), purchaseRequest.getAmount());
        if ("Purchase completed successfully".equals(result)) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }
}