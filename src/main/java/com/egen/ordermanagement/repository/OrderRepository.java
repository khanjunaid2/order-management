package com.egen.ordermanagement.repository;

import com.egen.ordermanagement.entity.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, String> {

    @Query("SELECT order FROM Order order where order.dateCreated>:start and order.dateCreated<:end")
    List<Order> getAllOrdersWithinInterval(Date start, Date end);

    @Query("SELECT order FROM Order order where order.billingAddress.zip=:zip order by order.payment.orderTotal DESC")
    List<Order> getTop10OrdersWithHighestDollarAmountInZip(String zip);
}
