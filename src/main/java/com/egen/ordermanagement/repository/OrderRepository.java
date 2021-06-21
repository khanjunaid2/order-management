package com.egen.ordermanagement.repository;

import com.egen.ordermanagement.model.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface OrderRepository extends JpaRepository<CustomerOrder, String> {

    @Query("SELECT order FROM CustomerOrder order WHERE order.creationDate BETWEEN :startTime AND :endTime")
    List<CustomerOrder> findAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime);
}
