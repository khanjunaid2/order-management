package com.egen.service;

import com.egen.model.Order;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> getAllOrder();

    Order getOrderById(String id);

    Optional<List<Order>> getAllOrdersWithinInterval(ZonedDateTime startTime, ZonedDateTime endTime);

    List<Order> top10OrdersWithHighestDollarAmountInZip(String zip);

    Order placeOrder(Order order);

    Order cancelOrder(String id);

    Order updateOrder(String orderId, Order order);

}
