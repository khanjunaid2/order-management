package org.example.repository;

import org.example.entity.CustomerOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface OrderRepository extends JpaRepository<CustomerOrder, String> {

    @Query("SELECT order FROM CustomerOrder order WHERE order.orderCreated BETWEEN :startTime AND :endTime")
    List<CustomerOrder> findAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime);

    Page<CustomerOrder> findAll(Pageable pageable);

}
