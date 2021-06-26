package com.egen.service;

import com.egen.dto.OrderDTO;
import com.egen.model.Order;

import java.sql.Timestamp;
import java.util.List;

public interface OrderService {
        List<OrderDTO> getAllOrders();

        Order getOrderById(String orderId);

        List<Order> getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime);

        List<Order> top10OrdersWithHighestDollarAmountInZip(String zip);

        OrderDTO placeOrder(Order order);

        OrderDTO cancelOrder(String orderId);

        OrderDTO updateOrder(String id, Order order);



}

