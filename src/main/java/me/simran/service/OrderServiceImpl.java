package me.simran.service;

import me.simran.exceptions.OrderNotFoundException;
import me.simran.model.Order;
import me.simran.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return (List<Order>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Order> getOrderById(String id) {
        Optional<Order> order = repository.findById(id);
        /**If nothing is returned*/
        if(order==null)
            throw new OrderNotFoundException("Order with id: "+id+" NOT FOUND");
        else
            return order;
    }

    @Transactional
    @Override
    public List<Order> getAllOrdersWithinInterval(String startTime, String endTime) {
        java.sql.Date start = Date.valueOf(startTime);
        java.sql.Date end = Date.valueOf(endTime);
        return repository.getAllOrdersWithinInterval(start, end);
    }

    @Transactional
    @Override
    public List<Order> getTop10OrdersWithHighestDollarAmountInZip(String zip) {
        return repository.getTop10OrdersWithHighestDollarAmountInZip(zip);
    }

    @Transactional
    @Override
    public Order placeOrder(Order order) {
        return repository.save(order);
    }

    @Transactional
    @Override
    public void delete(String id) {
        Optional<Order> order = repository.findById(id);
        if(order==null)
            throw new OrderNotFoundException("Order with id: "+id+" doesn't exist");
        else
            repository.delete(order.get());
    }

    @Transactional
    @Override
    public Order update(String id, Order order) {
        Optional<Order> orderObj = repository.findById(id);
        if(orderObj==null)
            throw new OrderNotFoundException("Order with id: "+id+" doesn't exist");
        else
            return repository.save(order);
    }
}
