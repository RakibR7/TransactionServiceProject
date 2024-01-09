package atu.ie.transactionserviceproject;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank, Long>{
    Optional<Bank> findByEmail(String email);

}
