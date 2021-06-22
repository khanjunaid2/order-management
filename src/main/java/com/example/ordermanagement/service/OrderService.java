package com.example.ordermanagement.service;

import com.example.ordermanagement.DTO.OrdersDto;
import com.example.ordermanagement.models.Orders;

import java.sql.Timestamp;
import java.util.List;


public interface OrderService {
    List<OrdersDto> getAllOrders();
    List<OrdersDto> getAllOrdersByPagingAndSorting(Integer pageNo, Integer pageSize, String sortBy);
    OrdersDto getOrderById(String id);
    List<OrdersDto> getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime);
    List<OrdersDto> top10OrdersWithHighestDollarAmountInZip(String zip);
    OrdersDto placeOrder(OrdersDto ordersDto);
    OrdersDto cancelOrder(String id);
    OrdersDto updateOrder(Orders orders, String id);
}
