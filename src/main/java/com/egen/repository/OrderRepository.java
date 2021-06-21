package com.egen.repository;

import com.egen.enums.OrderStatus;
import com.egen.model.Orders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.util.List;

public interface OrderRepository extends CrudRepository<Orders, String> {

@Query("SELECT ord FROM Orders ord JOIN Shipping add ON ord.shipping.shipping_id=add.shipping_id WHERE add.shipping_zip=:zip ORDER BY ord.order_total DESC")
public List<Orders> findTop10OrdersWithHighestDollarAmountInZip(@Param("zip") String zip);

@Query("SELECT ord FROM Orders ord  WHERE ord.creation_time BETWEEN :startTime AND :endTime")
public List<Orders> getAllOrdersWithInInterval(@Param("startTime") Timestamp startTime, @Param("endTime") Timestamp endTime);
}

