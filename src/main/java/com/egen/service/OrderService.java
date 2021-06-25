package com.egen.service;

import com.egen.dto.OrderDTO;
import com.egen.exception.BadRequestException;
import com.egen.exception.ResourceNotFoundException;
import com.egen.mapper.OrderMapper;
import com.egen.model.Items;
import com.egen.model.Order;
import com.egen.model.Payment;
import com.egen.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepositoryImpl;

    @Autowired
    ItemsService itemsService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    AddressService addressService;

    public List<Order> getAllOrders(){
        List<Order> list = (List<Order>) orderRepositoryImpl.findAll();
        if(list.size() == 0) {
            throw new ResourceNotFoundException("Order table is empty");
        }
        return list;
    }

    public Order getOrderById(long id){
        Optional<Order> existing = orderRepositoryImpl.findById(id);
        if(!existing.isPresent()) {
            throw new BadRequestException("Order with id " + id + " does not exists");
        }
        return existing.get();
    }

    public List<Order> getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime){
        List<Order> orderList = orderRepositoryImpl.getAllOrdersWithInInterval(startTime,endTime);
        if(orderList.isEmpty()) {
            throw new BadRequestException("Invalid time interval : "+startTime +" - "+endTime);
        }
        return orderList;
    }

    public List<Order> top10OrdersWithHighestDollarAmountInZip(String zip){
        List<Order> orderList = orderRepositoryImpl.findTop10OrdersWithHighestDollarAmountInZip(zip);
        if(orderList.isEmpty()) {
            throw new BadRequestException(zip + " does not exists");
        }
        return orderList;
    }

    @Transactional
    public OrderDTO placeOrder(Order order){

        //create items
       Set<Items> item=order.getItems();
        for(Items it : item) {
            it.setOrders(order);
        }

        //create payment method
        Set<Payment> paymentsList = order.getPayment();
        for(Payment paymnt : paymentsList) {
            paymnt.setOrders(order);
        }

        Order existing = orderRepositoryImpl.save(order);
        if(existing == null) {
            throw new BadRequestException("Order with id " + order.getId() + "already exists");
        }
        OrderMapper map=new OrderMapper();
        return map.setOrderDtoObject(existing);
    }

    @Transactional
    public Order cancelOrder(long id){
        Order existing = orderRepositoryImpl.cancel(id);
        if(existing == null) {
            throw new ResourceNotFoundException("Order with id " + id+ "has already been canceled");
        }
        return existing;
    }

    @Transactional
    public Order updateOrder(Order order){

        Order existing = orderRepositoryImpl.save(order);
        if(existing == null) {
            throw new ResourceNotFoundException("Order with id " + order.getId() + "does not exist");
        }
        return order;
    }

    @Transactional
    public List<Order> getAllOrdersByPaginationAndSorting(int pageNum, int pageSize, String sortBy) {

        Page<Order> pagedOrders = (Page<Order>) orderRepositoryImpl.findAll(PageRequest.of(pageNum, pageSize, Sort.by(sortBy)));

        if (!pagedOrders.hasContent()) {
            throw new ResourceNotFoundException("Order does not exist");
        }

         return pagedOrders.getContent();
    }
}
