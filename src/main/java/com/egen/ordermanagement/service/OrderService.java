package com.egen.ordermanagement.service;

import com.egen.ordermanagement.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    List<Order> getAllOrders(int pageNo, int pageSize);
    Order getOrderById(String id);
    List<Order> getAllOrdersWithinInterval(String startTime, String endTime);
    List<Order> getTop10OrdersWithHighestDollarAmountInZip(String zip);
    Order placeOrder(Order order);
    void delete(String id);
    Order update(String id, Order order);
}
