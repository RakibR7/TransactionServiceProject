package atu.ie.transactionserviceproject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepo extends JpaRepository<Person, Long> {
    Person findByEmployeeId(String employeeId);
}