package com.egen.Services;

import com.egen.DTO.OrderDTO;
import com.egen.Model.OrderStatus;

import java.time.ZonedDateTime;
import java.util.List;

interface OrderServiceInterface {
    List<OrderDTO> getAllOrders();

    OrderDTO getOrderById(String id);

    List<OrderDTO> getOrdersWithTimeInterval(ZonedDateTime startTime, ZonedDateTime endTime);

    List<OrderDTO> top10OrdersWithHighestDollarAmountInZip(String zip);

    OrderDTO addOrder(OrderDTO orderDTO);

    OrderStatus cancelOrder(String id);

    OrderStatus updateOrder(String id, OrderDTO orderDTO);

}