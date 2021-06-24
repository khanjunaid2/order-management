package com.egen.ordermanagment.services;

import com.egen.ordermanagment.dto.OrdersDTO;
import com.egen.ordermanagment.exception.OrderServiceException;
import com.egen.ordermanagment.model.*;
import com.egen.ordermanagment.repository.OrderRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceImplTest {
    @MockBean
    private OrderRepository repository;

    @Autowired
    OrderService service;

//    @TestConfiguration
//    static class OrderServiceImplTestConfiguration {
        @Bean
        public OrderService getService(){
            return new OrderServiceImpl();
        }
//    }


    OrdersDTO orderDto = new OrdersDTO();
    Orders ord = new Orders();

    @Before
    public void setup(){
        OrderItems orderItems = new OrderItems();
        Payment payment = new Payment();
        Address address = new Address();
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

        BeanUtils.copyProperties(ord, orderDto);

        Mockito.when(repository.findAll()).thenReturn(Collections.singletonList(ord));

        Mockito.when(repository.findById(ord.getId()))
                .thenReturn(Optional.of(ord));

        Mockito.when(repository.findAllByOrderCreatedBetween(ord.getOrderCreated(),
                ord.getOrderCreated())).thenReturn(Collections.singletonList(ord));

        Mockito.when(repository.findTop10OrdersWithHighestDollarAmountInZip(ord.getShippingAddress().getZip())).thenReturn(Collections.singletonList(ord));

        Mockito.when(repository.save(ord)).thenReturn(ord);

    }

    @After
    public void cleanup(){
        System.out.println("cleanup done");
    }

    @Test
    public void getAllOrders() throws Exception{
        List<Orders> result = service.getAllOrders();
        Assert.assertEquals("orders should match", ord, result);
    }

    @Test
    public void findAllPaginationSorting() throws Exception {
        List<OrdersDTO> result = service.findAllPaginationSorting(0,2,"orderCreated");
        Assert.assertEquals("Orders should match",Collections.singletonList(orderDto), result);
    }

    @Test
    public void findOne() throws Exception{
        OrdersDTO result = service.findOne(ord.getId());
        Assert.assertEquals("order id should match", orderDto ,result);
    }

    @Test(expected = OrderServiceException.class)
    public void findOneNotFound() throws Exception{
        OrdersDTO result = service.findOne("test");
        Assert.assertEquals("order id not matched", orderDto ,result);
    }

    @Test
    public void findWithinInterval() throws Exception{
        List<OrdersDTO> ordersWithinTime = service.findWithinInterval(ord.getOrderCreated(),
                ord.getOrderCreated());
        Assert.assertEquals("orders should match",Collections.singletonList(orderDto), ordersWithinTime);
    }

    @Test(expected = OrderServiceException.class)
    public void findWithinIntervalNotFound() {
        List<OrdersDTO> ordersWithinTime = service.findWithinInterval(ord.getOrderCreated(),
                ord.getOrderCreated());
        Assert.assertEquals("orders not matched",Collections.singletonList(orderDto), ordersWithinTime);
    }

    @Test
    public void findTop10OrdersWithHighestDollarAmountInZip() throws Exception{
        List<OrdersDTO> top10orders = service.findTop10OrdersWithHighestDollarAmountInZip("89119");
        Assert.assertEquals("Orders should matched", Collections.singletonList(orderDto), top10orders);
    }

    @Test(expected = OrderServiceException.class)
    public void findTop10OrdersWithHighestDollarAmountInZipNotFound(){
        List<OrdersDTO> top10orders = service.findTop10OrdersWithHighestDollarAmountInZip(ord.getShippingAddress().getZip());
        Assert.assertEquals("Orders not matched", Collections.singletonList(orderDto), top10orders);
    }

    @Test
    public void placeOrder() throws Exception{
        OrdersDTO order = service.placeOrder(orderDto);
        Assert.assertEquals("Error while creating order",orderDto,order);
    }

    @Test
    public void cancelOrder() throws Exception{
        OrdersDTO cancel = service.cancelOrder(ord.getId());
        Assert.assertEquals("Error while updating order as cancelled","CANCELLED",cancel.getOrderStatus().toString());
    }
}