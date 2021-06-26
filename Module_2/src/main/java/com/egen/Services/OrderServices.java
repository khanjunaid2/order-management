package com.egen.Services;

import com.egen.DTO.OrderDTO;
import com.egen.Model.OrderStatus;
import com.egen.Repository.OrderRepo;
import com.egen.exception.OrderServicesException;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class OrderServices implements OrderServiceInterface{

    private final OrderRepo orderrepo;

    public OrderServices (OrderRepo orderrepo){
        this.orderrepo = orderrepo;
    }


    @Override
    public List<OrderDTO> getAllOrders() {
        try{
            return orderrepo.getAllOrders();
        }
        catch(Exception e){
            System.out.println("Failed to fetch all the orders");
            throw new OrderServicesException("Internal Server Error Occurred", e);
        }
    }

    @Override
    public OrderDTO getOrderById(String id) {
        try{
            return orderrepo.getOrderById(id);
        }
        catch(Exception e){
            System.out.println("Failed to fetch the orders");
            throw new OrderServicesException("Internal Server Error Occurred", e);
        }
    }

    @Override
    public List<OrderDTO> getOrdersWithTimeInterval(ZonedDateTime startTime, ZonedDateTime endTime) {
        try {
            return orderrepo.getOrdersWithTimeInterval(startTime, endTime);
        }
        catch (Exception e){
            System.out.println("Failed to fetch the orders");
            throw new OrderServicesException("Internal Server Error Occurred", e);
        }
    }

    @Override
    public List<OrderDTO> top10OrdersWithHighestDollarAmountInZip(String zip) {
        try {
            return orderrepo.top10OrdersWithHighestDollarAmountInZip(zip);
        }
        catch (Exception e) {
            System.out.println("Failed to fetch the orders");
            throw new OrderServicesException("Internal Server Error Occurred", e);
        }
    }

    @Override
    public OrderDTO addOrder(OrderDTO order) {
        try{
            orderrepo.save(order);
            return order;
        }
        catch(Exception e){
            System.out.println("Failed to create the order");
            throw new OrderServicesException("Order Service Exception Occurred while placing the order", e);
        }
    }

    @Override
    public OrderStatus cancelOrder(String id) {
        try{
            OrderDTO order = orderrepo.getOrderById(id);
            order.setStatus(OrderStatus.CANCELLED);
            orderrepo.save(order);
            return OrderStatus.CANCELLED;
        }
        catch (Exception e){
            System.out.println("Failed to cancel the order");
            throw new OrderServicesException("Order Service Exception Occurred while cancelling the order", e);
        }
    }

    @Override
    public OrderStatus updateOrder(String id, OrderDTO order) {
        try{
            OrderDTO existingOrder = orderrepo.getOrderById(id);
            existingOrder.setStatus(OrderStatus.MODIFIED);
            orderrepo.save(order);
            return OrderStatus.MODIFIED;
            }
        catch (Exception e){
            System.out.println("Failed to Update the order");
            throw new OrderServicesException("Order Service Exception Occurred while updating the order", e);
        }
    }
}