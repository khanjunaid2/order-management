package com.egen.ordermanagment.services;

import com.egen.ordermanagment.dto.OrdersDTO;
import com.egen.ordermanagment.exception.OrderServiceException;
import com.egen.ordermanagment.model.*;
import com.egen.ordermanagment.repository.OrderRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.*;

@RunWith(SpringRunner.class)
public class OrderServiceImplTest {
    @MockBean
    private OrderRepository repository;

    @Qualifier("getService")
    @Autowired
    OrderService service;

    @TestConfiguration
    static class OrderServiceImplTestConfiguration {
        @Bean
        public OrderService getService(){
            return new OrderServiceImpl();
        }
    }

    private List<Orders> orders;
    private List<OrdersDTO> ordersDTOS;

    OrdersDTO orderDto;
    Orders ord;

    @Before
    public void setup(){
        orderDto = new OrdersDTO();
        OrderItems orderItems = new OrderItems();
        Payment payment = new Payment();
        Address address = new Address();
        ord = new Orders();
        ord.setId("order1").setOrderStatus(OrderStatus.PLACED)
                .setCustomerId("Customer1")
                .setOrderCreated(Timestamp.valueOf("2021-06-22 12:00:09"))
                .setOrderModified(Timestamp.valueOf("2021-06-22 12:00:09"))
                .setOrderTotal(10.0)
                .setOrderSubTotal(10.0)
                .setOrderTax(5.0)
                .setOrderTotal(10.50)
                .setOrderItemsList(Collections.singletonList(orderItems
                        .setOrderItemId("OrderItem1")
                        .setOrderItemName("Kellogs")
                        .setOrderItemQty(10)
                        .setOrderItemUnitPrice(15.0)))
                .setPaymentDetail(payment.setPaymentId("paymentId1")
                        .setPaymentAmount(100.0)
                        .setPaymentMethod(PaymentMethod.CREDIT)
                        .setBillingAddress(address
                                .setAddressId("Address1")
                                .setAddressLine1("4217 Grove Cir")
                                .setAddressLine2("#5")
                                .setCity("Las Vegas")
                                .setState("NV")
                                .setZip("89119")))
                .setShippingAddress(address
                        .setAddressId("Address1")
                        .setAddressLine1("4217 Grove Cir")
                        .setAddressLine2("#5")
                        .setCity("Las Vegas")
                        .setState("NV")
                        .setZip("89119"));

        orders = Collections.singletonList(ord);

        BeanUtils.copyProperties(orders, orderDto);

        Mockito.when(repository.findAll()).thenReturn(orders);

        Mockito.when(repository.findById(ord.getId()))
                .thenReturn(Optional.of(ord));

        Mockito.when(repository.findAllByOrderCreatedBetween(orders.get(0).getOrderCreated(),
                orders.get(0).getOrderCreated())).thenReturn(orders);

        Mockito.when(repository.findTop10OrdersWithHighestDollarAmountInZip(orders.get(0)
        .getShippingAddress().getZip())).thenReturn(orders);

        Mockito.when(repository.save(ord)).thenReturn(ord);

    }

    @After
    public void cleanup(){
        System.out.println("cleanup done");
    }

    @Test
    public void getAllOrders() throws Exception{
        List<Orders> result = service.getAllOrders();
        Assert.assertEquals("orders should match", result, result);
    }

    @Test
    public void findAllPaginationSorting() throws Exception {
        List<OrdersDTO> result = service.findAllPaginationSorting(0,2,"orderCreated");
        Assert.assertEquals("Orders should match",Collections.singletonList(orderDto), result);
    }

    @Test
    public void findOne() throws Exception{
        OrdersDTO result = service.findOne(orders.get(0).getId());
        System.out.println(result);
        Assert.assertEquals("order id should match", orderDto ,result);
    }

    @Test(expected = OrderServiceException.class)
    public void findOneNotFound() throws Exception{
        OrdersDTO result = service.findOne("test");
        Assert.assertEquals("order id not matched", orderDto ,result);
    }

    @Test
    public void findWithinInterval() throws Exception{
        List<OrdersDTO> ordersWithinTime = service.findWithinInterval(orders.get(0).getOrderCreated(),
                orders.get(0).getOrderCreated());
        System.out.println(ordersWithinTime);
        Assert.assertEquals("orders should match",ordersWithinTime,Collections.singletonList(orderDto));
    }

    @Test(expected = OrderServiceException.class)
    public void findWithinIntervalNotFound() {
        List<OrdersDTO> ordersWithinTime = service.findWithinInterval(orders.get(0).getOrderCreated(),
                ord.getOrderCreated());
        Assert.assertEquals("orders not matched",ordersWithinTime,Collections.singletonList(orderDto));
    }

    @Test
    public void findTop10OrdersWithHighestDollarAmountInZip() throws Exception{
        List<OrdersDTO> top10orders = service.findTop10OrdersWithHighestDollarAmountInZip("89119");
        Assert.assertEquals("Orders should matched",top10orders, Collections.singletonList(orderDto));
    }

    @Test(expected = OrderServiceException.class)
    public void findTop10OrdersWithHighestDollarAmountInZipNotFound(){
        List<OrdersDTO> top10orders = service.findTop10OrdersWithHighestDollarAmountInZip(orders.get(0)
                .getShippingAddress().getZip());
        Assert.assertEquals("Orders not matched",top10orders, Collections.singletonList(orderDto));
    }

    @Test
    public void placeOrder() throws Exception{
        OrdersDTO order = service.placeOrder(orderDto);
        Assert.assertEquals("Error while creating order",order,order);
    }

    @Test
    public void cancelOrder() throws Exception{
        OrdersDTO cancel = service.cancelOrder(orders.get(0).getId());
        Assert.assertEquals("Error while updating order as cancelled","CANCELLED",cancel.getOrderStatus().toString());
    }
}