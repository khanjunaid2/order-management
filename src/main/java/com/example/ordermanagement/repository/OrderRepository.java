package com.example.ordermanagement.repository;

import com.example.ordermanagement.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, String> {

//    @Query(nativeQuery = true, value = "select ord from Orders ord where ord.createdAt between : startTime and : endTime")
//    List<Orders> findAllOrdersWithinIntervals(Timestamp startTime, Timestamp endTime);

    List<Orders> findOrdersByCreatedAtBetween(Timestamp startTime, Timestamp endTime);

    @Query(nativeQuery = true, value = "select ord from Orders ord, Address addr where ord.shipping_address_id = addr.address_id" +
            "and addr.zip =:zip order by desc limit 10")
    List<Orders> findTop10OrdersWithHighestDollarAmountInZip(String zip);

}
