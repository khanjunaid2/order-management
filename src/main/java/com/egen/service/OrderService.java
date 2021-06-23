package com.egen.service;

import com.egen.dto.OrderDTO;

import java.sql.Timestamp;
import java.util.List;

public interface OrderService {
    Boolean placeOrder(OrderDTO order);
    public List<OrderDTO> getAllOrders();
    public List<OrderDTO> getOrderPage(int pagenumber);
    public OrderDTO getOrderById(String eid);
    public OrderDTO updateOrder(String oid, OrderDTO orderDTO);
    public List<OrderDTO> getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime);
    public List<OrderDTO> top10OrdersWithHighestDollarAmountInZip(String zip);
    public OrderDTO cancelOrder(String id);

}
