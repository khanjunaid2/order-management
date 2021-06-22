package org.example.service;

import org.example.entity.CustomerOrder;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;

public interface OrderService {

    public List<CustomerOrder> getAllOrders();

    public CustomerOrder getOrderById(String id);

    public List<CustomerOrder> getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime);

    public List<CustomerOrder> getTop10OrdersWithHighestDollarAmountInZip(String zip);

    public CustomerOrder placeOrder(CustomerOrder order);

    public CustomerOrder updateOrder(CustomerOrder order);

    public CustomerOrder cancelOrder(String id);

}
