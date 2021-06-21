package com.egen.ordermanagement.service;

import com.egen.ordermanagement.model.GroceryOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Timestamp;
import java.util.List;


public interface OrderManagementService {
    GroceryOrder saveOrder(GroceryOrder order);

    GroceryOrder getOrderById(String id);

    GroceryOrder getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime);

    GroceryOrder top10OrdersWithHighestDollarAmountInZip(String zip);

    void cancelOrder(GroceryOrder order);

    GroceryOrder updateOrder(GroceryOrder order);

    GroceryOrder createRandomOrders(int num);

    List<GroceryOrder> getAllOrders();

    GroceryOrder updateOrderById(String id);
}
