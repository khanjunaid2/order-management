package com.egen.service;

import com.egen.model.Orders;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.util.List;

public interface OrderService {
    Orders placeOrder(Orders order);
    public List<Orders> getAllOrders();
    public Orders getOrderById(String eid);
    public Orders updateOrder(String oid, Orders order);
    public List<Orders> getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime);
    public List<Orders> top10OrdersWithHighestDollarAmountInZip(String zip);
    public Orders cancelOrder(Orders order);

}
