package com.egen.ordermanagement.repository;

import com.egen.ordermanagement.model.entity.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping, String> {
}
