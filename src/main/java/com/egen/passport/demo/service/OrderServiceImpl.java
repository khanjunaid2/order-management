package com.egen.passport.demo.service;


import com.egen.passport.demo.dto.CustomerDTO;
import com.egen.passport.demo.dto.OrderDTO;
import com.egen.passport.demo.entity.Address;
import com.egen.passport.demo.entity.CustomerOrder;
import com.egen.passport.demo.entity.Item;
import com.egen.passport.demo.entity.Payment;
import com.egen.passport.demo.exception.OrderServiceException;
import com.egen.passport.demo.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository;


    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Boolean createOrder(OrderDTO orderDto) {

        try{

        CustomerOrder orderEntity = new CustomerOrder();
        BeanUtils.copyProperties(orderDto , orderEntity);
        Payment payment = new Payment();
        List<Item> items = new ArrayList<>(orderDto.getItems().size());
        List<Address> addresses = new ArrayList<>(orderDto.getItems().size());


        BeanUtils.copyProperties(orderDto.getPaymentDetails() , orderEntity.getPaymentDetails());



            for (int i = 0; i < orderDto.getItems().size(); i++) {
            Item itm = new Item();
            BeanUtils.copyProperties(orderDto.getItems().get(i) , itm);
            itm.setOrder(orderEntity);
            items.add(itm);
        }

        for (int i = 0; i < orderDto.getAddresses().size(); i++) {
            Address address = new Address();
            BeanUtils.copyProperties(orderDto.getAddresses().get(i) , address);
            address.setOrder(orderEntity);
            addresses.add(address);
        }

        orderEntity.setItems(items);
        orderEntity.setAddresses(addresses);
        BeanUtils.copyProperties(orderDto.getPaymentDetails() , payment);
        payment.setOrder(orderEntity);
        orderEntity.setPaymentDetails(payment);

        orderRepository.save(orderEntity);
        }catch (Exception e){
            throw new OrderServiceException("Internal Server Error", e);
        }
        return true;
    }

    @Override
    public Boolean createOrders(List<OrderDTO> orderDto) {
        try{

            CustomerDTO cutomer = new CustomerDTO().setFirstName("").setLastName("").setId(1);


        List<CustomerOrder> orderEntityList = new ArrayList<>(orderDto.size());
        for(int i=0;i<orderDto.size();i++){
            CustomerOrder orderEntity = new CustomerOrder();
            BeanUtils.copyProperties(orderDto.get(i) , orderEntity);
            Payment payment = new Payment();
            List<Item> items = new ArrayList<>(orderDto.get(i).getItems().size());
            List<Address> addresses = new ArrayList<Address>(orderDto.get(i).getAddresses().size());



            for (int j = 0; j < orderDto.get(i).getItems().size(); j++) {
                Item itm = new Item();
                BeanUtils.copyProperties(orderDto.get(i).getItems().get(j) , itm);
                itm.setOrder(orderEntity);
                items.add(itm);
            }

            for (int k = 0; k < orderDto.get(i).getAddresses().size(); k++) {
                Address address = new Address();
                BeanUtils.copyProperties(orderDto.get(i).getAddresses().get(k) , address);
                address.setOrder(orderEntity);
                addresses.add(address);
            }

            orderEntity.setItems(items);
            orderEntity.setAddresses(addresses);
            BeanUtils.copyProperties(orderDto.get(i).getPaymentDetails() , payment);
            payment.setOrder(orderEntity);
            orderEntity.setPaymentDetails(payment);
            orderEntityList.add(orderEntity);
        }

        orderRepository.saveAll(orderEntityList);
        }catch (Exception e){
            throw new OrderServiceException("Internal Server Error", e);
        }
        return true;
    }

    @Override
    public CustomerOrder getOrderById(Long id) {
        try {
            CustomerOrder order = orderRepository.findCustomerOrderById(id);
            return order;
        } catch (Exception e) {
            throw new OrderServiceException("order not found", e);
        }
    }

    @Override
    public List<CustomerOrder> getAllOrders() {
        try {
            List<CustomerOrder> orders = orderRepository.findAll();
            return  orders;
        } catch (Exception e) {
            throw new OrderServiceException("order not found", e);
        }
    }


    @Override
    public List<CustomerOrder> getAllOrders(int from , int to) {
        try {
            List<CustomerOrder> orders = orderRepository.findAll(PageRequest.of(from,to)).toList();

            List<CustomerOrder> filter = orders.stream().filter(obj -> obj.getSubTotal() > 40)
                    .collect(Collectors.toList());

            List<Address> filter1 = orders.stream().map(o -> o.getAddresses()).
                    flatMap(x -> x.stream()).filter(obj -> obj.getState().equalsIgnoreCase("IL"))
                    .collect(Collectors.toList());

            List<Address> map = orders.stream().map(obj -> obj.getAddresses()).
                    flatMap(ob -> ob.stream()).map(x -> x.setState("IN")).collect(Collectors.toList());

            List<CustomerOrder> filtrAsc = orders.stream().filter(obj -> obj.getSubTotal() > 40).
                    sorted((x1 ,x2) -> Double.compare(x1.getSubTotal() , x2.getSubTotal())).collect(Collectors.toList());

            List<CustomerOrder> filtrDesc = orders.stream().filter(obj -> obj.getSubTotal() > 40).
                    sorted((x1 ,x2) -> Double.compare(x2.getSubTotal() , x1.getSubTotal())).collect(Collectors.toList());


            return  orders;
        } catch (Exception e) {
            throw new OrderServiceException("order not found", e);
        }
    }

    @Override
    public List<CustomerOrder> getAllOrdersSortBy(int from , int to, String sortBy) {
        try {
            List<CustomerOrder> orders = orderRepository.findAll(PageRequest.of(from,to , Sort.by(sortBy))).toList();
            //PageRequest.of(0, 3, Sort.by("customerId").descending());
            //PageRequest.of(0, 5, Sort.by("customerId").descending().and(Sort.by("subTotal")));
            return  orders;
        } catch (Exception e) {
            throw new OrderServiceException("order not found", e);
        }
    }



}
