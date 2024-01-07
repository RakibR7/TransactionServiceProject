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

    public void savePerson(Person person){
        personRepo.save(person);
    }
    public Person getPersonByEmployeeId(String employeeId){
        return personRepo.findByEmployeeId(employeeId);
    }

    public List<Person> getAllPersons() {
        return personRepo.findAll();
    }

    @Transactional      //this makes it so if the transaction fails anywhere it will undo everything, like a real transaction
    public String processPurchase(Long personId, long amount) {
        // Find the person by ID
        Person person = personRepo.findById(personId)
                .orElseThrow(() -> new IllegalArgumentException("Person not found with ID: " + personId));

        // Check if the person has enough balance for the purchase
        if (person.getBalance() >= (amount)) {
            // Subtract the purchase amount from the person's balance
            person.setBalance(person.getBalance() - (amount));
            // Save the updated person entity
            personRepo.save(person);
            return "Purchase completed successfully";
        } else {
            return "Insufficient funds";
        }
    }
}
