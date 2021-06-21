package com.egen.ordermanagement.repository;

import com.egen.ordermanagement.model.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends CrudRepository<Payment,Long> {
}
