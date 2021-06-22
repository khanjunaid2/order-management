package com.egen.repository;

import com.egen.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository<zip> extends CrudRepository<Order, String> {

    @Query("SELECT ord FROM Order ord JOIN Address add ON ord.shippingAddress.id=add.id" +
            " WHERE " +
            "add.zip=:paramZip " +
            "ORDER BY " +
            "ord.totalAmount DESC")
    List<Order> findByzip(String zip);

    @Query("SELECT ord FROM Order ord " +
            "WHERE ord.createdDate BETWEEN :startTime AND :endTime")
    List<Order> getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime);
}
