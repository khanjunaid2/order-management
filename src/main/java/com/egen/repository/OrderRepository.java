package com.egen.repository;

import com.egen.model.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface OrderRepository extends CrudRepository<Orders, String> {

    Page<Orders> findAll(Pageable pageable);

    @Query("SELECT ord FROM Orders ord JOIN Shipping add ON ord.shipping.shippingId=add.shippingId WHERE add.zip=:zip ORDER BY ord.total DESC")
    List<Orders> findTop10OrdersWithHighestDollarAmountInZip(@Param("zip") String zip);

    @Query("SELECT ord FROM Orders ord  WHERE ord.creationTime BETWEEN :startTime AND :endTime")
    List<Orders> getAllOrdersWithInInterval(@Param("startTime") Timestamp startTime, @Param("endTime") Timestamp endTime);
}

