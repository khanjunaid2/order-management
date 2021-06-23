package com.egen.passport.demo.repository;

import com.egen.passport.demo.entity.CustomerOrder;
import com.egen.passport.demo.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
