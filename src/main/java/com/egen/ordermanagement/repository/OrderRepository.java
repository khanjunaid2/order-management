package com.egen.ordermanagement.repository;

import com.egen.ordermanagement.enums.DeliveryType;
import com.egen.ordermanagement.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAll();
    @Query("SELECT order FROM Order order WHERE order.creationDate BETWEEN :startTime AND :endTime")
    List<Order> findAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime);
//    Order findCustomerOrderByCustomerId(Long id);
//    Order findCustomerOrderById(Long id);
//    Order findCustomerOrderByDeliveryType(DeliveryType deliveryType);
}
