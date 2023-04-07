package com.berat.repository;

import com.berat.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<Account,Long> {
    List<Account> findAllByCustomerId(Long customerId);
    Optional<Account> findByIban(String iban);
}
