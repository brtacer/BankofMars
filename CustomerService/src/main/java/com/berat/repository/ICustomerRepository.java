package com.berat.repository;

import com.berat.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findByAuthId(Long authId);
}
