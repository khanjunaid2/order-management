package com.egen.passport.demo.repository;

import com.egen.passport.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByFirstName(String FirstName);
    List<Customer> findAll();
}
