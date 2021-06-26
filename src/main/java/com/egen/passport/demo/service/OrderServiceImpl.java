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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Boolean createOrder(OrderDTO orderDto) {

        try {
            int a = 10 / 0;
            CustomerOrder orderEntity = new CustomerOrder();
            BeanUtils.copyProperties(orderDto, orderEntity);
            Address shpAddrs = new Address();
            Address billAddrs = new Address();
            Payment payment = new Payment();
            List<Item> items = new ArrayList<>(orderDto.getItems().size());


            for (int i = 0; i < orderDto.getItems().size(); i++) {
                Item itm = new Item();
                BeanUtils.copyProperties(orderDto.getItems().get(i), itm);
                itm.setOrder(orderEntity);
                items.add(itm);
            }

            orderEntity.setItems(items);
            BeanUtils.copyProperties(orderDto.getShippingAddress(), shpAddrs);
            BeanUtils.copyProperties(orderDto.getBillingAddress(), billAddrs);
            BeanUtils.copyProperties(orderDto.getPaymentDetails(), payment);

            shpAddrs.setOrder(orderEntity);
            billAddrs.setOrder(orderEntity);
            payment.setOrder(orderEntity);
            orderEntity.setPaymentDetails(payment);
            orderEntity.setShippingAddress(shpAddrs);
            orderEntity.setBillingAddress(billAddrs);

            orderRepository.save(orderEntity);
        } catch (Exception e) {
            throw new OrderServiceException("Internal Server Error", e);
        }
        return true;
    }

    @Override
    public Boolean createOrders(List<OrderDTO> orderDto) {
        try {

            CustomerDTO cutomer = new CustomerDTO().setFirstName("").setLastName("").setId(1);


            List<CustomerOrder> orderEntityList = new ArrayList<>(orderDto.size());
            for (int i = 0; i < orderDto.size(); i++) {
                CustomerOrder orderEntity = new CustomerOrder();
                BeanUtils.copyProperties(orderDto.get(i), orderEntity);
                Address shpAddrs = new Address();
                Address billAddrs = new Address();
                Payment payment = new Payment();
                List<Item> items = new ArrayList<>(orderDto.get(i).getItems().size());


                for (int j = 0; j < orderDto.get(i).getItems().size(); j++) {
                    Item itm = new Item();
                    BeanUtils.copyProperties(orderDto.get(i).getItems().get(j), itm);
                    itm.setOrder(orderEntity);
                    items.add(itm);
                }

                orderEntity.setItems(items);
                BeanUtils.copyProperties(orderDto.get(i).getShippingAddress(), shpAddrs);
                BeanUtils.copyProperties(orderDto.get(i).getBillingAddress(), billAddrs);
                BeanUtils.copyProperties(orderDto.get(i).getPaymentDetails(), payment);

                shpAddrs.setOrder(orderEntity);
                billAddrs.setOrder(orderEntity);
                payment.setOrder(orderEntity);
                orderEntity.setPaymentDetails(payment);
                orderEntity.setShippingAddress(shpAddrs);
                orderEntity.setBillingAddress(billAddrs);
                orderEntityList.add(orderEntity);
            }

            orderRepository.saveAll(orderEntityList);
        } catch (Exception e) {
            throw new OrderServiceException("Internal Server Error", e);
        }
        return true;
    }

    @Override
    public CustomerOrder getOrderById(Long id) {
        try {
            OrderDTO orderDTO = new OrderDTO();
            int a = 10 / 0;
            return orderRepository.getById(id);
        } catch (Exception e) {
            throw new OrderServiceException("order not found", e);
        }
    }

    @Override
    public List<CustomerOrder> getAllOrders() {
        try {
            OrderDTO orderDTO = new OrderDTO();
            return orderRepository.findAll();
        } catch (Exception e) {
            throw new OrderServiceException("order not found", e);
        }
    }
}
