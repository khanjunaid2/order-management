package org.example.service;

import org.example.entity.CustomerOrder;
import org.example.enums.OrderStatus;
import org.example.exception.InternalServerException;
import org.example.exception.OrderServiceException;
import org.example.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Override
    public List<CustomerOrder> getAllOrders() {
        try{
            return orderRepository.findAll();
        }
        catch(InternalServerException internalServerException){
            System.out.println("Failed to fetch all the orders");
            throw new OrderServiceException("Internal Server Error Occurred");
        }
    }

    @Override
    public CustomerOrder getOrderById(String id) {
        try {
            Optional<CustomerOrder> order = orderRepository.findById(id);
            if(!order.isPresent()) {
                throw new OrderServiceException("Order Cannot be Found");
            }
            return order.get();
        }catch(InternalServerException internalServerException){
            System.out.println("Failed to fetch the orders");
            throw new OrderServiceException("Internal Server Error Occurred");
        }
    }

    @Override
    public List<CustomerOrder> getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime) {
        try{
            return orderRepository.findAllOrdersWithInInterval(startTime, endTime);
        }
        catch(InternalServerException internalServerException){
            System.out.println("Failed to fetch the orders within Time Interval");
            throw new OrderServiceException("Internal Server Error Occurred");
        }
    }

    @Override
    public List<CustomerOrder> getTop10OrdersWithHighestDollarAmountInZip(String zip) {
        try {
            List<CustomerOrder> allOrders = orderRepository.findAll();

            if(allOrders.size() == 0)
                throw new OrderServiceException("No Order's found at the zip.");

            return allOrders.stream()
                    .filter(order -> order.getBillingAddress().getZip().equals(zip))
                    .sorted((o1, o2) -> Double.compare(o2.getOrderTotal(), o1.getOrderTotal()))
                    .limit(10)
                    .collect(Collectors.toList());
        }
        catch (InternalServerException ise) {
            System.out.println("Cannot Found top 10 Order's at the Zip.");
            throw new InternalServerException("Internal Server Error occurred");
        }
    }

    @Override
    public CustomerOrder placeOrder(CustomerOrder order) {
        try{
            orderRepository.save(order);
            return order;
        }
        catch(OrderServiceException orderServiceException){
            System.out.println("Failed to create the order");
            throw new OrderServiceException("Order Service Exception Occurred while placing the order");
        }
    }

    @Override
    public CustomerOrder updateOrder(CustomerOrder order) {
        try{
            Optional<CustomerOrder> existingOrder = orderRepository.findById(order.getOrderId());
            if (!existingOrder.isPresent()){
                throw new OrderServiceException("Order does not Exist");
            }
            else if (existingOrder.get().getOrderStatus().equals(OrderStatus.ORDER_SHIPPED)){
                throw new OrderServiceException("The order has already been Shipped");
            }
            else{
                return orderRepository.save(order);
            }
        }
        catch(OrderServiceException orderServiceException){
            System.out.println("Failed to Update the Order");
            throw new OrderServiceException("Order Service Exception Occurred while updating the order");
        }
    }

    @Override
    public CustomerOrder cancelOrder(String id) {
        try{
            Optional<CustomerOrder> customerOrder = orderRepository.findById(id);
            if(!customerOrder.isPresent()){
                throw new OrderServiceException("Order cannot be found");
            }
            else{
                customerOrder.get().setOrderStatus(OrderStatus.ORDER_CANCELLED);
                orderRepository.save(customerOrder.get());
                return customerOrder.get();
            }
        }
        catch(OrderServiceException orderServiceException){
            System.out.println("Failed to Delete the Order");
            throw new OrderServiceException("Order Service Exception Occurred while deleting the order");
        }
    }
}
