package com.egen.ordermanagement.service;

import com.egen.ordermanagement.dto.OrderDto;
import com.egen.ordermanagement.enums.OrderStatus;
import com.egen.ordermanagement.exceptions.OrderServiceException;
import com.egen.ordermanagement.model.Address;
import com.egen.ordermanagement.model.Item;
import com.egen.ordermanagement.model.Orders;
import com.egen.ordermanagement.model.Payment;
import com.egen.ordermanagement.repository.OrdersRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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


    public OrdersServiceImpl(OrdersRepo ordersRepo) {
        this.ordersRepo=ordersRepo;
    }

    public OrdersServiceImpl() {

    }
    public OrdersServiceImpl(OrdersRepo ordersRepo,ItemService itemService,CustomerService customerService,
                             PaymentService paymentService,AddressService addressService) {
        this.ordersRepo=ordersRepo;
        this.paymentService=paymentService;
        this.addressService=addressService;
        this.itemService=itemService;
        this.customerService=customerService;
    }
    @Transactional(readOnly = true)
    public List<Orders> findAll() {

    return Optional.ofNullable(ordersRepo.findAll())
            .orElseThrow(() -> new OrderServiceException("No orders are present currently"));
    }

    @Transactional(readOnly = true)
    public Orders findOne(Long id) {
        return ordersRepo.findById(id)
                .orElseThrow(() ->
                        new OrderServiceException("No orders with id: "+id+" were present in the inventory"));
    }

    @Transactional(readOnly = true)
    public List<Orders> findWithinInterval(Timestamp startTime, Timestamp endTime) {

        return  Optional.ofNullable(ordersRepo.findAllByDateOrderedBetween(startTime,endTime))
                .orElseThrow(() ->
                        new OrderServiceException("Orders between the specific time range now found" ));
    }

    @Transactional(readOnly = true)
    public List<Orders> findAllByShippingAddressZipcodeAndSubTotal(String zip) {
        return Optional.ofNullable(ordersRepo.findAllByShippingAddressZipcodeAndSubTotal(zip))
                .orElseThrow(() -> new OrderServiceException("No orders were found in the zip code: "+zip));
    }

    @Transactional
    public Boolean createOrder(OrderDto orderDto) {
    try {

        Orders new_order = null;
        double sub_total = 0;

        //Gets current date and adds 5 days to current date and sets it as delivery date
        Date date = new Date();
        long ltime = date.getTime() + 5 * 24 * 60 * 60 * 1000;
        Date expectedDelivery = new Date(ltime);
        Timestamp date_ordered = new Timestamp(date.getTime());
        Timestamp delivery_date = new Timestamp(expectedDelivery.getTime());

        ///Update item's quantity left in stock and get subtotal for the quantity ordered
        Iterator<Long> it = Arrays.stream(orderDto.getItems()).iterator();
        while (it.hasNext()) {
            Long item_id = it.next();
            itemService.updateItem(item_id, orderDto.getItemQuantity());
            Item item2 = itemService.getItem(item_id);
            sub_total += item2.getItemPrice() * orderDto.getItemQuantity();
        }

        //default shipping charges and tax
        double tax = 1.5, shippingCharges = 3.0;

        //Calculate the total amount
        double total = tax + sub_total + shippingCharges;

        //If customer doesnt exist then treat them as guest customer with Id =0
        Long cust_id = Long.valueOf(0);

        //Check if the customer is a member
        boolean existingCustomer = customerService.findCustomer(orderDto.getCustomerId());

        //Creates new order, payemnt and address details based on the previoud values.
        if (existingCustomer) {
            List<Payment> payments = new ArrayList<>();
            Address shippingAddress = new Address();
            Address billingAddress = new Address();
            int itemQuantity=0;

            for(int i=0;i<orderDto.getPayments().size();i++)
            BeanUtils.copyProperties(orderDto.getPayments().get(i),payments.add(orderDto.getPayments().get(i)));

            BeanUtils.copyProperties(orderDto.getCustomerId(),cust_id);
            BeanUtils.copyProperties(orderDto.getShippingAddress(),shippingAddress);
            BeanUtils.copyProperties(orderDto.getBillingAddress(),billingAddress);
            BeanUtils.copyProperties(orderDto.getItemQuantity(),itemQuantity);


            new_order = new Orders(cust_id, date_ordered, delivery_date, itemQuantity, sub_total, tax,
                    shippingCharges, total, OrderStatus.PLACED, orderDto.getShipmentMethod(), shippingAddress);
            ordersRepo.save(new_order);

            //If billing address is same as shipping then avoid duplicate address
            //Creating payment based on the result of isBillingSameAsShippingAddress()
            if (orderDto.isBillingSameAsShippingAddress()) {
                Address address = addressService.createAddress(shippingAddress);
                paymentService.createPayment(payments, address, new_order);
            } else {
                new_order = new Orders(cust_id, date_ordered, delivery_date, itemQuantity, sub_total, tax,
                        shippingCharges, total, OrderStatus.PLACED, orderDto.getShipmentMethod(),
                        shippingAddress);
                addressService.createAddress(shippingAddress);
                Address billing = addressService.createAddress(billingAddress);
                paymentService.createPayment(payments, billing, new_order);
            }
        }
        //Updates Order_id in item table
        Iterator<Long> it2 = Arrays.stream(orderDto.getItems()).iterator();
        while (it2.hasNext()) {
            Long itemId = Long.valueOf(it2.next());
            itemService.updateOrderIdInItem(itemId, new_order);
        }
        return true;
    }catch (Exception ex){
        throw new OrderServiceException("Failed to create order. Please try again",ex);
    }
    }

    @Transactional
    public Orders cancelOrder(Long id) {
        try {
            Optional<Orders> orders = ordersRepo.findById(id);
            if(!orders.isPresent())
                throw new OrderServiceException();
            orders.get().setOrderStatus(OrderStatus.CANCELLED);
            return ordersRepo.save(orders.get());
        }catch (OrderServiceException ex){
            throw new OrderServiceException("The order id: "+id +"your want to cancel is not found in our records",ex);
        }
    }

    @Transactional
    public Orders updateOrder(Orders order,Long id) {
        try {
            Optional<Orders> orders = ordersRepo.findById(id);
            if(!orders.isPresent())
                throw new OrderServiceException();
            orders.get().setOrderStatus(order.getOrderStatus());
            return ordersRepo.save(orders.get());
        }catch (OrderServiceException ex){
            throw new OrderServiceException("The order id: "+id +" you want to modify is not found in our records",ex);
        }
    }

    @Transactional
    public List<Orders> findAllByPageLimit(Integer pageNo, Integer pageSize) {
     //   try {
            Pageable paging = PageRequest.of(pageNo, pageSize);
         //   Page<Orders> pagedResult = ordersRepo.findAll(paging);

            return Optional.ofNullable(ordersRepo.findAll(paging))
                    .orElseThrow(() ->
                            new OrderServiceException("No orders were found"))
                    .getContent();
//            if (pagedResult.hasContent()) {
//                return pagedResult.getContent();
//            } else
//                throw new Exception();
//        }catch (Exception ex){
//            throw new OrderServiceException("No orders were found ",ex);
       //}
    }

   @Transactional
    public List<Orders> sortByValues(Integer pageNo, Integer pageSize, String sortBy) {
        //try{
            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
         //   Page<Orders> pagedResult = ordersRepo.findAll(paging);
            return Optional.ofNullable(ordersRepo.findAll(paging))
                    .orElseThrow(() ->
                            new OrderServiceException("No orders were found based on the sort values"))
                    .getContent();
//            if (pagedResult.hasContent()) {
//                return  pagedResult.getContent();
//            } else
//                throw new Exception();
//        }catch (Exception ex){
//            throw new OrderServiceException("No orders were found based on the sort values",ex);
//        }
    }
}

