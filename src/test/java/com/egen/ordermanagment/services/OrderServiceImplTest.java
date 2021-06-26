package com.egen.ordermanagment.services;

import com.egen.ordermanagment.dto.OrdersDTO;
import com.egen.ordermanagment.exception.OrderServiceException;
import com.egen.ordermanagment.model.*;
import com.egen.ordermanagment.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class OrderServiceImplTest {
    @MockBean
    private OrderRepository repository;

    @Autowired
    OrderService service;

    @Bean
    public OrderService getService(){
        return new OrderServiceImpl();
    }


    OrdersDTO orderDto = new OrdersDTO();
    Orders ord = new Orders();

    @BeforeEach
    public void mockData(){
        ord.setId("order1").setOrderStatus(OrderStatus.PLACED)
                .setCustomerId("Customer1")
                .setOrderCreated(Timestamp.valueOf("2021-06-22 12:00:09"))
                .setOrderModified(Timestamp.valueOf("2021-06-22 12:00:09"))
                .setOrderTotal(10.0)
                .setOrderSubTotal(10.0)
                .setOrderTax(5.0)
                .setOrderTotal(10.50)
                .setOrderItemsList(Collections.singletonList(new OrderItems()
                        .setOrderItemId("OrderItem1")
                        .setOrderItemName("Kellogs")
                        .setOrderItemQty(10)
                        .setOrderItemUnitPrice(15.0)))
                .setPaymentDetail(new Payment().setPaymentId("paymentId1")
                        .setPaymentAmount(100.0)
                        .setPaymentMethod(PaymentMethod.CREDIT)
                        .setBillingAddress(new Address()
                                .setAddressId("Address1")
                                .setAddressLine1("4217 Grove Cir")
                                .setAddressLine2("#5")
                                .setCity("Las Vegas")
                                .setState("NV")
                                .setZip("89119")))
                .setShippingAddress(new Address()
                        .setAddressId("Address1")
                        .setAddressLine1("4217 Grove Cir")
                        .setAddressLine2("#5")
                        .setCity("Las Vegas")
                        .setState("NV")
                        .setZip("89119"));

//        orderDto = new OrderDTOMapper().entityToDTO(ord);
        BeanUtils.copyProperties(ord, orderDto);

        Mockito.when(repository.findAll()).thenReturn(Collections.singletonList(ord));

        Mockito.when(repository.findById(ord.getId()))
                .thenReturn(Optional.of(ord));

        Mockito.when(repository.findAllByOrderCreatedBetween(ord.getOrderCreated(),
                ord.getOrderCreated())).thenReturn(Collections.singletonList(ord));

        Mockito.when(repository.findTop10OrdersWithHighestDollarAmountInZip(ord.getShippingAddress().getZip())).thenReturn(Collections.singletonList(ord));

        Mockito.when(repository.save(ord)).thenReturn(ord);
    }
    @AfterEach
    public void cleanUp(){
        System.out.println("Clean up done");
    }

    @Test
    void getAllOrders() {
        List<Orders> result = service.getAllOrders();
        Assertions.assertEquals(Collections.singletonList(ord), result, "Orders should match");
    }

    @Test
    void findAllPaginationSorting() {
        List<OrdersDTO> result = service.findAllPaginationSorting(0,2,"orderCreated");
        Assertions.assertEquals(Collections.singletonList(orderDto), result, "Orders should match");
    }

    @Test
    void findOne() {
        System.out.println(orderDto.toString());
        OrdersDTO result = service.findOne(ord.getId());
        Assertions.assertEquals(orderDto, result, "Order id should match");
    }

    @Test
    void findWithinInterval() {
        List<OrdersDTO> ordersWithinTime = service.findWithinInterval(ord.getOrderCreated(),ord.getOrderCreated());
        Assertions.assertEquals(Collections.singletonList(orderDto), ordersWithinTime, "Orders should match");
    }

    @Test
    void findTop10OrdersWithHighestDollarAmountInZip(){
        List<OrdersDTO> top10orders = service.findTop10OrdersWithHighestDollarAmountInZip("89119");
        Assertions.assertEquals(Collections.singletonList(orderDto), top10orders, "Orders should not match");
    }

    @Test
    void updateOrder() {
        OrdersDTO modifiedOrder = service.updateOrder(ord.getId(), orderDto);
        Assertions.assertEquals(orderDto, modifiedOrder, "orders should be updated");
    }

    @Test
    void cancelOrder() {
        OrdersDTO cancelledOrder = service.cancelOrder(ord.getId());
        Assertions.assertEquals(orderDto, cancelledOrder, "Orders should be cancelled");
    }

    @Test
    void placeOrder() {
        boolean create = service.placeOrder(orderDto);
        Assertions.assertEquals(orderDto, create, "Order should be created");
    }
}