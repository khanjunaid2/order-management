package com.egen.repository;

import com.egen.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, String> {

//    @Query("SELECT ORDERID FROM ORDERS WHERE ORDERCREATEDON=:startTime AND ORDERCREATEDON=:endTime")
//    Optional<List<Order>> findAllOrdersWithinInterval(@Param("startTime") ZonedDateTime startTime, @Param("endTime") ZonedDateTime endTime);
//
//    @Query("SELECT * " +
//            "FROM ORDERS " +
//            "WHERE ORDERBILLINGADDRESS=(SELECT * FROM ADDRESS WHERE ADDRESSZIP=:zip)" +
//            "ORDER BY ORDERTOTAL DESC" +
//            "LIMIT 100")
//    List<Order> top10OrdersWithHighestDollarAmountInZip(String zip);

//    @Query("INSERT INTO ORDERS VALUES(:order.getOrderId(), :order.getOrderStatus(), :order.getOrderCustomer())")
//    Order placeOrder(Order order);
//
//    @Query("DELETE FROM ORDERS WHERE ORDERID=:id")
//    Order cancelOrder(String id);
//
//    @Query("SELECT ORDERID FROM ORDERS WHERE ORDERID=:orderId")
//    Order updateOrder(String orderId, Order order);
}
