package com.egen.ordermanagement.repository;

import com.egen.ordermanagement.model.entity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingRepository extends JpaRepository<Billing, String> {
}
