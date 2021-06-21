package com.egen.service;

import com.egen.model.GroceryOrder;

import java.sql.Timestamp;
import java.util.List;

public interface IOrderManagementService {

     List<GroceryOrder> getAllOrders();

     GroceryOrder getOrderById(String orderId);

     List<GroceryOrder> getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime);

     List<GroceryOrder> top10OrdersWithHighestDollarAmountInZip(String zip);

     GroceryOrder saveOrder(GroceryOrder groceryOrder);

     GroceryOrder cancelOrder(GroceryOrder groceryOrder);

     GroceryOrder updateOrder(GroceryOrder groceryOrder);

     String createRandomOrders(int num);
}
