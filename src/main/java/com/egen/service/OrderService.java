package com.egen.service;

import com.egen.dto.OrderDto;

import java.sql.Timestamp;
import java.util.List;

public interface OrderService {
    Boolean placeOrder(OrderDto order);
    public List<OrderDto> getAllOrders();
    public List<OrderDto> getOrderPage(int pagenumber);
    public OrderDto getOrderById(String eid);
    public OrderDto updateOrder(String oid, OrderDto orderDTO);
    public List<OrderDto> getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime);
    public List<OrderDto> top10OrdersWithHighestDollarAmountInZip(String zip);
    public OrderDto cancelOrder(String id);

}
