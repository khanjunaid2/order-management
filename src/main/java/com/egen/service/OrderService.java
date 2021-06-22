package com.egen.service;
import com.egen.dto.OrderItemDTO;
import com.egen.model.OrderItem;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface OrderService {

       List<OrderItem> getAllOrders();

       Optional<OrderItem> getOrderById(String id);

       List<OrderItem> getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime);

       List<OrderItem> top10OrdersWithHighestDollarAmountInZip(String zip);
       OrderItemDTO placeOrder(OrderItemDTO orderItem);

       List<OrderItem> pageFindAll(Integer page, Integer size);
//       OrderItem cancelOrder(OrderItem order);
//
//       OrderItem updateOrder(OrderItem order);
       String createRandomOrders(int num);

}
