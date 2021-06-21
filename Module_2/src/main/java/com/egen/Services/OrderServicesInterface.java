package com.egen.Services;

import com.egen.Model.Order;
import com.egen.Model.OrderStatus;

import java.time.ZonedDateTime;
import java.util.List;

interface OrderServiceInterface {
    List<Order> getAllOrders();

    Order getOrderById(String id);

    List<Order> getOrdersWithTimeInterval(ZonedDateTime startTime, ZonedDateTime endTime);

    List<Order> top10OrdersWithHighestDollarAmountInZip(String zip);

    Order addOrder(Order order);

    OrderStatus cancelOrder(String id);

    OrderStatus updateOrder(String id, Order order);

}