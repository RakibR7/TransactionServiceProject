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

    @Transactional
    public String processPurchase(Long personId, int purchaseAmount) {
        Person person = personRepo.findById(personId).orElse(null);

        if (person == null) {
            return "Customer not found";
        }

        if (person.getBalance().compareTo(purchaseAmount) >= 0) {
            // Subtract purchase amount from current balance
            person.setBalance(person.getBalance().subtract(purchaseAmount));
            personRepo.save(person);

            // Optionally, create a transaction record (not shown)

            return "Purchase completed successfully";
        } else {
            return "Insufficient funds";
        }
    }

}
