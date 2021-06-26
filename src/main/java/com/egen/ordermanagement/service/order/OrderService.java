package com.egen.ordermanagement.service.order;

import com.egen.ordermanagement.dto.OrderDTO;
import com.egen.ordermanagement.dto.OrderStatusDTO;

import java.sql.Timestamp;
import java.util.List;

public interface OrderService {

    List<OrderDTO> getAllOrders();
    List<OrderDTO> getAllOrdersWithPaginationAndSorted(int pageNumber, int pageSize, String sortBy);
    OrderDTO getOrderById(String id);
    List<OrderDTO> getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime);
    List<OrderDTO> top10OrdersWithHighestDollarAmountInZip(String zip);
    OrderDTO placeOrder(OrderDTO order);
    OrderDTO cancelOrder(String orderId);
    OrderDTO updateOrder(OrderDTO order);
    void updateOrderStatus(OrderStatusDTO orderStatusDTO);
}
