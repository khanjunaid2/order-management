package com.egen.ordermanagement.service;

import com.egen.ordermanagement.dto.OrderDTO;

import java.sql.Timestamp;
import java.util.List;

public interface OrderService {

    List<OrderDTO> getAllOrders();
    OrderDTO getOrderById(String id);
    List<OrderDTO> getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime);
    List<OrderDTO> top10OrdersWithHighestDollarAmountInZip(String zip);
    OrderDTO placeOrder(OrderDTO order);
    OrderDTO cancelOrder(String orderId);
    OrderDTO updateOrder(OrderDTO order);
}
