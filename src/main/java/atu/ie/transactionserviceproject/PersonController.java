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

    @GetMapping("/{employeeId}")
    public ResponseEntity<?> getPerson(@PathVariable String employeeId) {
        if (employeeId.length() > 5 || employeeId.isBlank()) {
            return ResponseEntity.badRequest().body("EmployeeId is invalid");
        }

        Person person = personService.getPersonByEmployeeId(employeeId);

        if (person == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(person);
    }

    @GetMapping("/findAllPersons")
    public ResponseEntity<?> getAllPersons() {

        List<Person> persons = personService.getAllPersons();

        if (persons == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(persons);
    }

    @PostMapping("/createPerson")
    public ResponseEntity<String>create(@Valid @RequestBody Person person){
        personService.savePerson(person);
        return new ResponseEntity<>("person created successfully", HttpStatus.OK);
    }

    @PostMapping("/buynow")
    public ResponseEntity<String> buyNow(@RequestParam Long personId, @RequestParam long amount) {
        String result = personService.processPurchase(personId, amount);
        if (result.equals("Purchase completed successfully")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }
}