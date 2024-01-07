package atu.ie.transactionserviceproject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PersonService {
    private final PersonRepo personRepo;

    public PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public Person getPersonByEmail(String email){
        return personRepo.findByEmail(email);
    }

    @Transactional      //this makes it so if the transaction fails anywhere it will undo everything, like a real transaction
    public String processPurchase(String email, long amount) {
        // Find the person by email
        Person person = personRepo.findByEmail(email);
                //.orElseThrow(() -> new IllegalArgumentException("Person not found with ID: " + email));    not working
        // Check if the person has enough balance for the purchase
        if (person.getBalance() >= (amount)) {
            person.setBalance(person.getBalance() - (amount));
            // Save the updated person entity
            personRepo.save(person);
            return "Purchase completed successfully";
        } else {
            return "Insufficient funds";
        }
    }
}
