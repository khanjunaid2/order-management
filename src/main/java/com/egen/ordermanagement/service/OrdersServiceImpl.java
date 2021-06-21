package com.egen.ordermanagement.service;

import com.egen.ordermanagement.dto.OrderDto;
import com.egen.ordermanagement.enums.OrderStatus;
import com.egen.ordermanagement.exceptions.OrderNotFoundException;
import com.egen.ordermanagement.model.Address;
import com.egen.ordermanagement.model.Item;
import com.egen.ordermanagement.model.Orders;
import com.egen.ordermanagement.repository.OrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    OrdersRepo ordersRepo;

    @Autowired
    ItemService itemService;

    @Autowired
    CustomerService customerService;

    @Autowired
    AddressService addressService;

    @Autowired
    PaymentService paymentService;

    public List<Orders> findAll() {
        return (List<Orders>) ordersRepo.findAll();
    }

    @Override
    public Orders findOne(Long id) {
        Optional<Orders> orders = ordersRepo.findById(id);
        if (!orders.isPresent())
            throw new OrderNotFoundException("The order id: "+id +" your looking for is not found in our records");
        return orders.get();
    }

    @Override
    public List<Orders> findWithinInterval(Timestamp startTime, Timestamp endTime) {
       List<Orders> orders = ordersRepo.findAllByDateOrderedBetween(startTime,endTime);
       if(orders.isEmpty())
           throw new OrderNotFoundException("Orders between the specific time range now found");
       else
           return orders;
    }

    @Override
    public List<Orders> findTop10OrdersWithHighestDollarAmountInZip(String zip) {
        List<Orders> maxAmountOrders = ordersRepo.findMaxTotalAmountInParticularArea(zip);
        if(maxAmountOrders.isEmpty())
            throw new OrderNotFoundException("No orders were found in the zip code: "+zip);
        else
            return maxAmountOrders;
    }

    @Override
    public Orders createOrder(OrderDto orderDto) {

        Orders new_order =null;
        double sub_total = 0;

        //Gets current date and adds 5 days to current date and sets it as delivery date
        Date date = new Date();
        long ltime=date.getTime()+5*24*60*60*1000;
        Date expectedDelivery = new Date(ltime);
        Timestamp date_ordered = new Timestamp(date.getTime());
        Timestamp delivery_date = new Timestamp(expectedDelivery.getTime());

        ///Update item's quantity left in stock and get subtotal for the quantity ordered
        Iterator<Integer> it = Arrays.stream(orderDto.getItems()).iterator();
        while (it.hasNext()){
            Long item_id = Long.valueOf(it.next());
            itemService.updateItem(item_id,orderDto.getItemQuantity());
            Item item2 = itemService.getItem(item_id);
            sub_total += item2.getItemPrice() * orderDto.getItemQuantity();
        }

        //default shipping charges and tax
        double tax = 1.5,shippingCharges=3.0;

        //Calculate the total amount
        double total = tax + sub_total + shippingCharges;

        //If customer doesnt exist then treat them as guest customer with Id =0
        Long cust_id = Long.valueOf(0);

        //Check if the customer is a member
        boolean existingCustomer = customerService.findCustomer(orderDto.getCustomerId());

        //Creates new order, payemnt and address details based on the previoud values.
        if (existingCustomer) {
            cust_id = orderDto.getCustomerId();
            new_order = new Orders(cust_id, date_ordered, delivery_date, orderDto.getItemQuantity(), sub_total, tax,
                    shippingCharges, total, OrderStatus.PLACED, orderDto.getShipmentMethod(), orderDto.getShippingAddress());
            ordersRepo.save(new_order);

            //If billing address is same as shipping then avoid duplicate address
            //Creating payment based on the result of isBillingSameAsShippingAddress()
            if (orderDto.isBillingSameAsShippingAddress()) {
                Address address = addressService.createAddress(orderDto.getShippingAddress());
                paymentService.createPayment(orderDto.getPayments(), address, new_order);
            } else {
                new_order = new Orders(cust_id, date_ordered, delivery_date, orderDto.getItemQuantity(), sub_total, tax,
                        shippingCharges, total, OrderStatus.PLACED, orderDto.getShipmentMethod(), orderDto.getShippingAddress());

                addressService.createAddress(orderDto.getShippingAddress());

                Address billingAddress = addressService.createAddress(orderDto.getShippingAddress());
                paymentService.createPayment(orderDto.getPayments(), billingAddress, new_order);
            }
        }
        //Updates Order_id in item table
        Iterator<Integer> it2 = Arrays.stream(orderDto.getItems()).iterator();
        while (it2.hasNext()) {
            Long itemId = Long.valueOf(it2.next());
            itemService.updateOrderIdInItem(itemId,new_order);
        }
        return new_order;
    }

    @Transactional
    public Orders cancelOrder(Long id) {
        Optional<Orders> orders = ordersRepo.findById(id);
        if(!orders.isPresent())
            throw new OrderNotFoundException("The order id: "+id +"your want to cancel is not found in our records");
        orders.get().setOrderStatus(OrderStatus.CANCELLED);
        return ordersRepo.save(orders.get());
    }

    @Transactional
    public Orders updateOrder(OrderDto orderDto,Long id) {
        Optional<Orders> orders = ordersRepo.findById(id);
        if(!orders.isPresent())
            throw new OrderNotFoundException("The order id: "+id +" you want to modify is not found in our records");
        orders.get().setOrderStatus(orderDto.getOrderStatus());
        return ordersRepo.save(orders.get());
    }
}
