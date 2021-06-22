package com.egen.service;

import com.egen.exception.OrderServiceException;
import com.egen.model.Address;
import com.egen.model.Customer;
import com.egen.model.Item;
import com.egen.model.Order;
import com.egen.enums.OrderStatus;
import com.egen.model.Payment;
import com.egen.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DefaultClassInfoStrategy;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.api.PodamUtils;
import uk.co.jemos.podam.typeManufacturers.IntTypeManufacturerImpl;
import uk.co.jemos.podam.typeManufacturers.TypeManufacturer;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Service

public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return orderRepo.getAllOrders();
    }

    @Override
    @Transactional
    public Order getOrderById(String orderId) {
        try{
            return orderRepo.getOrderById(orderId);
        }catch (Exception e ){
            throw new OrderServiceException("No order by Id ",e);
        }

    }

    @Override
    @Transactional
    public List<Order> getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime) {
        return orderRepo.getAllByCreatedAtBetween(startTime,endTime);
    }

    @Override
    @Transactional
    public List<Order> top10OrdersWithHighestDollarAmountInZip(String zip) {
        List<Order> orders = orderRepo.getAllOrders();
        List<Order> groceryOrdersInZip = new LinkedList<>();
        for(Order order: orders){
            if(order.getShippingAddress().getZip().equalsIgnoreCase(zip)) groceryOrdersInZip.add(order);
        }
        Collections.sort(groceryOrdersInZip, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return Double.compare(o2.getTotalAmount(), o1.getTotalAmount());
            }
        });
        List<Order> newList = new ArrayList<>(10);
        for(int i=0 ; i < 10; i++){
            newList.add(groceryOrdersInZip.get(i));
        }
        return newList;
    }

    @Override
    @Transactional
    public Order saveOrder(Order order) {
        return orderRepo.save(order);
    }

    @Override
    @Transactional
    public Order cancelOrder(Order order) {
        Order order1 = orderRepo.getOrderById(order.getId());
        order1.setOrderStatus(OrderStatus.CANCEL);
        return orderRepo.save(order1);
    }

    @Override
    @Transactional
    public Order updateOrder(Order order) {
        return orderRepo.save(order);
    }

    @Override
    @Transactional
    public String createRandomOrders(int num){
        PodamFactory factory = new PodamFactoryImpl();
        TypeManufacturer<Integer> manufacturer = new IntTypeManufacturerImpl() {

            @Override
            public Integer getInteger(AttributeMetadata attributeMetadata) {

                if (attributeMetadata.getPojoClass().getName().equalsIgnoreCase("java.sql.Timestamp")) {
                    return PodamUtils.getIntegerInRange(0, 999);
                } else {
                    return super.getInteger(attributeMetadata);
                }
            }
        };
        factory.getStrategy().addOrReplaceTypeManufacturer(int.class, manufacturer);
        DefaultClassInfoStrategy classInfoStrategy = DefaultClassInfoStrategy.getInstance();
        classInfoStrategy.addExcludedField(Order.class, "id" );
        classInfoStrategy.addExcludedField(Item.class, "id" );
        classInfoStrategy.addExcludedField(Payment.class, "id" );
        classInfoStrategy.addExcludedField(Address.class, "id" );
        classInfoStrategy.addExcludedField(Customer.class, "id" );
        factory.setClassStrategy(classInfoStrategy);
        for(int i =0 ; i< num ; i++){
            Order order = factory.manufacturePojoWithFullData(Order.class);
            this.saveOrder(order);
        }
        return "success";
    }
}
