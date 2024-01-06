package atu.ie.transactionserviceproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final PersonRepo personRepository;

    @Autowired
    public DataLoader(PersonRepo personRepo) {
        this.personRepository = personRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        // Insert your test data here
        Person testData = new Person(1L, "Paul", 23, "Paul@atu.ie", "Mr", "12345", "Lecturer", "Electronics",0);
        Person testData2 = new Person(2L, "Jack", 24, "jacko@atu.ie","Mr", "123456", "Lecturer", "engineering",0);
        Person testData3 = new Person(3L, "Santa", 70, "gift@atu.ie","Mrs", "1234567", "plumber", "factory",0);

        personRepository.save(testData);
        personRepository.save(testData2);
        personRepository.save(testData3);
    }
}
