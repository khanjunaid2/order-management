package me.simran.service;

import me.simran.model.Order;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getAllOrders();
    Optional<Order> getOrderById(String id);
    List<Order> getAllOrdersWithinInterval(String startTime, String endTime);
    List<Order> getTop10OrdersWithHighestDollarAmountInZip(String zip);
    Order placeOrder(Order order);
    void delete(String id);
    Order update(String id, Order order);
}
