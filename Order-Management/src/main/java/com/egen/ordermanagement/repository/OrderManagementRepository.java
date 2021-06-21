package com.egen.ordermanagement.repository;

import com.egen.ordermanagement.model.GroceryOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderManagementRepository extends JpaRepository<GroceryOrder,String> {
}
