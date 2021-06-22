package com.egen.repository;

import com.egen.model.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment,String> {
}
