package com.egen.ordermanagement.service;

import com.egen.ordermanagement.dto.CustomerDTO;
import com.egen.ordermanagement.dto.OrderDTO;
import com.egen.ordermanagement.exception.InternalServerException;
import com.egen.ordermanagement.model.Address;
import com.egen.ordermanagement.model.Item;
import com.egen.ordermanagement.model.Order;
import com.egen.ordermanagement.model.Payment;
import com.egen.ordermanagement.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;

    @Override
    public Boolean createOrder(OrderDTO orderDto) {
        try{

            Order order = new Order();
            BeanUtils.copyProperties(orderDto , order);
            Address shippingAddress = new Address();
            Address billingAddress = new Address();
            Payment payment = new Payment();
            List<Item> items = new ArrayList<>(orderDto.getItems().size());

            for (int i = 0; i < orderDto.getItems().size(); i++) {
                Item item = new Item();
                BeanUtils.copyProperties(orderDto.getItems().get(i) , item);
                item.setOrder(order);
                items.add(item);
            }

            order.setItems(items);
            BeanUtils.copyProperties(orderDto.getShippingAddress() , shippingAddress);
            BeanUtils.copyProperties(orderDto.getBillingAddress(), billingAddress);
            BeanUtils.copyProperties(orderDto.getPaymentDetails() , payment);

            shippingAddress.setOrder(order);
            billingAddress.setOrder(order);
            payment.setOrder(order);
            order.setPaymentDetails(payment);
            order.setShippingAddress(shippingAddress);
            order.setBillingAddress(billingAddress);
            orderRepository.save(order);
        }catch (Exception e){
            throw new InternalServerException("Internal Server Error");
        }
        return true;
    }

    @Override
    public Order getOrderById(Long id) {
        try {
            return orderRepository.getById(id);
        } catch (Exception e) {
            throw new InternalServerException("order not found");
        }
    }

    @Override
    public Boolean createOrders(List<OrderDTO> orderDto) {
        try{

//            CustomerDTO customer = new CustomerDTO().setFirstName("").setLastName("").setId(1);

            List<Order> orderList = new ArrayList<>(orderDto.size());
            for(int i=0;i<orderDto.size();i++){
                Order orderEntity = new Order();
                BeanUtils.copyProperties(orderDto.get(i) , orderEntity);
                Address shippingAddress = new Address();
                Address billingAddress = new Address();
                Payment payment = new Payment();
                List<Item> items = new ArrayList<>(orderDto.get(i).getItems().size());

                for (int j = 0; j < orderDto.get(i).getItems().size(); j++) {
                    Item itm = new Item();
                    BeanUtils.copyProperties(orderDto.get(i).getItems().get(j) , itm);
                    itm.setOrder(orderEntity);
                    items.add(itm);
                }

                orderEntity.setItems(items);
                BeanUtils.copyProperties(orderDto.get(i).getShippingAddress() , shippingAddress);
                BeanUtils.copyProperties(orderDto.get(i).getBillingAddress(), billingAddress);
                BeanUtils.copyProperties(orderDto.get(i).getPaymentDetails() , payment);

                shippingAddress.setOrder(orderEntity);
                billingAddress.setOrder(orderEntity);
                payment.setOrder(orderEntity);
                orderEntity.setPaymentDetails(payment);
                orderEntity.setShippingAddress(shippingAddress);
                orderEntity.setBillingAddress(billingAddress);
                orderList.add(orderEntity);
            }

            orderRepository.saveAll(orderList);
        }catch (Exception e){
            throw new InternalServerException("Internal Server Error");
        }
        return true;
    }

    @Override
    public List<Order> getAllOrders() {
        try {
            return orderRepository.findAll();
        } catch (Exception e) {
            throw new InternalServerException("order not found");
        }
    }
    @Override
    public List<OrderDTO> getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime) {

        try {
            List<OrderDTO> orderDTOList= new ArrayList<>();
            List<Order> orderList = orderRepository.findAllOrdersWithInInterval(startTime, endTime);
            BeanUtils.copyProperties(orderList, orderDTOList);
            return orderDTOList;

        } catch (InternalServerException ise) {
            throw new InternalServerException("Internal Server Error occurred");
        }
    }

//    @Autowired
//    public OrderRepository orderRepository;
////    public OrderServiceImpl(OrderRepository orderRepository) {
////        this.orderRepository = orderRepository;
////    }
//
//    public OrderServiceImpl(){}
//
//    public OrderServiceImpl(OrderRepository orderRepository) {
//        this.orderRepository = orderRepository;
//    }
//
//    @Override
//    public List<Order> getAllOrders() {
//        return orderRepository.findAll();
//    }
//
//    public Optional<Order> findById(String orderId){
//        return orderRepository.findById(orderId);
//    }
//
////    @Override
////    public List<Order> getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime) {
////        return null;
////    }
////
////    @Override
////    public List<Order> top10OrdersWithHighestDollarAmountInZip(String zip) {
////        List<Order> allOrders = orderRepository.getAllOrders();
////        PriorityQueue<Order> top10orders = new PriorityQueue<>((a, b) -> (int)b.getOrderTotal() - (int)a.getOrderTotal());
////        for(Order one: allOrders)
////            if(one.getShippingDetails().getZipcode().equals(zip)) top10orders.add(one);
////
////        List<Order> res = new ArrayList<>();
////        for(int i = 0; i < 10; i++){
////            if(top10orders.isEmpty()) break;
////            res.add(top10orders.remove());
////        }
////        return res;
////        return null;
////    }
//
//    @Override
//    public Order saveOrder(Order order) {
//
//        Order order1 = new Order();
//        order1.setName(order.getName());
//        return orderRepository.save(order1);
//    }
////
////    @Override
////    public Order cancelOrder(Order order) {
////        return null;
////    }
////
////    @Override
////    public Order updateOrder(Order order) {
////        return null;
////    }




}
