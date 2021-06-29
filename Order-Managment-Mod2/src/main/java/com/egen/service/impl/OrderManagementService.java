package com.egen.service.impl;

import com.egen.model.GroceryOrder;
import com.egen.model.OrderStatus;
import com.egen.repo.IOrderManagementRepo;
import com.egen.service.IOrderManagementService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class OrderManagementService implements IOrderManagementService {

    private final IOrderManagementRepo orderManagementRepo;

    public OrderManagementService(IOrderManagementRepo orderManagementRepo){
        this.orderManagementRepo = orderManagementRepo;
    }

    @Override
    public List<GroceryOrder> getAllOrders() {

        return (List<GroceryOrder>) orderManagementRepo.findAll();
    }

    @Override
    public GroceryOrder getOrderById(String orderId) {
        return  orderManagementRepo.findById(orderId).get();
    }

    @Override
    public List<GroceryOrder> getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime) {
        return orderManagementRepo.getAllOrdersWithInInterval(startTime,endTime);
    }

    @Override
    public List<GroceryOrder> top10OrdersWithHighestDollarAmountInZip(String zip) {
        List<GroceryOrder> orders = (List<GroceryOrder>) orderManagementRepo.findAll();
        List<GroceryOrder> groceryOrdersInZip = new LinkedList<>();
        for(GroceryOrder order: orders){
            if(order.getShippingAddress().getZip().equalsIgnoreCase(zip)) groceryOrdersInZip.add(order);
        }
        Collections.sort(groceryOrdersInZip, new Comparator<GroceryOrder>() {
            @Override
            public int compare(GroceryOrder o1, GroceryOrder o2) {
                return Double.compare(o2.getTotalAmount(), o1.getTotalAmount());
            }
        });
        List<GroceryOrder> newList = new ArrayList<>(10);
        for(int i=0 ; i < 10; i++){
            newList.add(groceryOrdersInZip.get(i));
        }
        return newList;
    }

    @Override
    public GroceryOrder saveOrder(GroceryOrder groceryOrder) {
        return orderManagementRepo.save(groceryOrder);
    }

    @Override
    public GroceryOrder cancelOrder(GroceryOrder groceryOrder) {
        //logic to check if cancel is possible
        groceryOrder.setOrderStatus(OrderStatus.CANCEL);
        return orderManagementRepo.save(groceryOrder);
    }

    @Override
    public GroceryOrder updateOrder(GroceryOrder groceryOrder) {
        //logic to check if update is possible
        return orderManagementRepo.save(groceryOrder);
    }


}
