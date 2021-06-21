package com.egen.service;

import com.egen.enums.OrderStatus;
import com.egen.model.Orders;
import com.egen.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    public OrderRepository orderRepository;

    //GET all the orders
    @Override
    public List<Orders> getAllOrders(){
        List<Orders> orders = (List<Orders>) orderRepository.findAll();
        return orders;
    }

    //GET order by iD
    public Orders getOrderById(String eid){
        Optional<Orders> order = orderRepository.findById(eid);
        if(order.isPresent()){
            return order.get();
        }
        return null;
    }

    //POST order
    public Orders placeOrder(Orders order){
        order.setOrder_status(OrderStatus.PLACED);
        orderRepository.save(order);
        return order;
    }
    //GET order by Time interval
    public List<Orders> getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime){
        List<Orders> orders = orderRepository.getAllOrdersWithInInterval(startTime, endTime);
        return orders;
    }

    //GET top orders by ZIP
    public List<Orders> top10OrdersWithHighestDollarAmountInZip(String zip){
        List<Orders> orders = orderRepository.findTop10OrdersWithHighestDollarAmountInZip(zip);
        return orders;
    }

    public Orders cancelOrder(Orders order){
        order.setOrder_status(OrderStatus.CANCELLED);
        orderRepository.save(order);
        return order;
    }


    //UPDATE order
    public Orders updateOrder(String oid, Orders order){
        orderRepository.save(order);
        return order;
    }

}