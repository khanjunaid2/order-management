package com.egen.ordermanagment.services;

import com.egen.ordermanagment.dto.OrdersDTO;
import com.egen.ordermanagment.model.Orders;
import com.egen.ordermanagment.response.Response;

import java.sql.Timestamp;
import java.util.List;

public interface OrderService {
    List<Orders> getAllOrders();
    List<OrdersDTO> findAllPaginationSorting(int page, int size, String sortBy);
    OrdersDTO findOne(String id);
    List<OrdersDTO> findWithinInterval(Timestamp startTime,Timestamp endTime);
    List<OrdersDTO> findTop10OrdersWithHighestDollarAmountInZip(String zip);
    OrdersDTO placeOrder(OrdersDTO order);
    OrdersDTO updateOrder(String id, OrdersDTO order);
    OrdersDTO cancelOrder(String id);
}
