package com.egen.ordermanagement.repository;

import com.egen.ordermanagement.model.entity.CustomerOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<CustomerOrder, String> {

    @Query("SELECT order FROM CustomerOrder order WHERE order.creationDate BETWEEN :startTime AND :endTime")
    List<CustomerOrder> findAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime);

    Page<CustomerOrder> findAll(Pageable pageable);
    List<CustomerOrder> findAll();
}
