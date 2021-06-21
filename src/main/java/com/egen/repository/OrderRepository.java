package com.egen.repository;

import com.egen.model.OrderItem;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;

public interface OrderRepository extends CrudRepository<OrderItem, String> {
//     List<Order> getAllOrders();
//
//    Order getOrderById(String id);
    @Query("select ord from OrderItem ord JOIN fetch ord.payments JOIN fetch ord.items " +
            "where ord.orderPlacedTime > :startTime AND ord.orderPlacedTime < :endTime")
     List<OrderItem> getAllOrdersWithInInterval(@Param("startTime") Timestamp startTime, @Param("endTime") Timestamp endTime);

//     List<Order> top10OrdersWithHighestDollarAmountInZip(String zip);
//    Order placeOrder(Order order);
//
//    Order cancelOrder(Order order);
//
//    Order updateOrder(Order order);


}
