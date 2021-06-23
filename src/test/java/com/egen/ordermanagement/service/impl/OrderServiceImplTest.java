package com.egen.ordermanagement.service.impl;

import com.egen.ordermanagement.dto.OrderDTO;
import com.egen.ordermanagement.exception.OrderRequestProcessException;
import com.egen.ordermanagement.exception.OrderServiceException;
import com.egen.ordermanagement.model.entity.*;
import com.egen.ordermanagement.model.enums.OrderStatus;
import com.egen.ordermanagement.model.enums.PaymentType;
import com.egen.ordermanagement.repository.OrderRepository;
import com.egen.ordermanagement.service.OrderService;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
class OrderServiceImplTest {

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

     @Bean
     public OrderService getService(){
         return new OrderServiceImpl(orderRepository);
     }

    CustomerOrder customerOrder = new CustomerOrder();
    OrderDTO orderDTO = new OrderDTO();

    @BeforeEach
    public void mockData() {

        customerOrder.setOrderId("testOrder1")
                     .setCreationDate(Timestamp.valueOf("2021-06-22 23:53:38"))
                     .setModificationDate(Timestamp.valueOf("2021-06-22 23:53:38"))
                     .setCustomerId("customer1")
                     .setTotal(250.50)
                     .setSubtotal(235.20)
                     .setTax(14.50)
                     .setStatus(OrderStatus.PENDING)
                     .setItems(Collections.singletonList(new Item().setItemId("itemId1").setItemName("item1").setItemQuantity(4).setItemUnitPrice(12.50)))
                     .setPayments(Collections.singletonList(new Payment().setPaymentId("paymentId1").setAmount(250.50).setPaymentType(PaymentType.CREDIT)
                        .setBilling(new Billing().setBillingId("billingId1").setAddressLine1("river st").setAddressLine2("line2").setCity("deplanes").setState("Illinois").setZip(60016))))
                     .setShipping(new Shipping().setShippingId("shippingId1").setAddressLine1("line1").setAddressLine2("line2").setCity("chicago").setState("Illinois").setZip("60016"));

        BeanUtils.copyProperties(customerOrder, orderDTO);

        Mockito.when(orderRepository.findAll())
               .thenReturn(Collections.singletonList(customerOrder));

        Mockito.when(orderRepository.findById(customerOrder.getOrderId())).thenReturn(Optional.of(customerOrder));

        Mockito.when(orderRepository.findAllOrdersWithInInterval(Timestamp.valueOf("2021-06-22 23:53:38"),
                Timestamp.valueOf("2021-06-24 23:53:38"))).thenReturn(Collections.singletonList(customerOrder));

        Mockito.when(orderRepository.save(customerOrder)).thenReturn(customerOrder);
    }

    @AfterEach
    public void cleanUp() {}

    @Test
    void getAllOrders() {
        System.out.println(orderDTO);
        List<OrderDTO> result = orderService.getAllOrders();
        Assertions.assertEquals(Collections.singletonList(orderDTO), result, "Order list should match");
    }

//    @Test(expected = OrderServiceException.class)
//    public void findAllNotFound() {
//        List<OrderDTO> result = orderService.getAllOrders();
//    }

    @Test
    void getAllOrdersWithPaginationAndSorted() {
        List<OrderDTO> result = orderService.getAllOrdersWithPaginationAndSorted(0, 2, "creationDate");
        Assertions.assertEquals(Collections.singletonList(orderDTO), result, "Order list should match");
    }

    @Test
    void getOrderById() {
        OrderDTO extractedOrderDTO = orderService.getOrderById(customerOrder.getOrderId());
        Assertions.assertEquals(orderDTO, extractedOrderDTO, "Order id should match");
    }

//    @Test(expected = OrderRequestProcessException.class)
//    public void getOrderByIdNotFound(){
//        OrderDTO result = orderService.getOrderById("877889");
//    }

    @Test
    void getAllOrdersWithInInterval() {
        List<OrderDTO> result = orderService.getAllOrdersWithInInterval(Timestamp.valueOf("2021-06-22T23:53:38.2682"), Timestamp.valueOf("2021-06-24T23:53:38.2682"));
        Assertions.assertEquals(Collections.singletonList(orderDTO), result, "Order list should match");
    }

    @Test
    void top10OrdersWithHighestDollarAmountInZip() {
        List<OrderDTO> result = orderService.top10OrdersWithHighestDollarAmountInZip("60016");
        Assertions.assertEquals(Collections.singletonList(orderDTO), result, "Order list should match");
    }

    @Test
    @Transactional
    void placeOrder() {
        OrderDTO newOrder = orderService.placeOrder(orderDTO);
        Assertions.assertEquals(orderDTO, newOrder, "Order should create");
    }

    @Test
    @Transactional
    void cancelOrder() {
        OrderDTO cancelledOrder = orderService.cancelOrder(customerOrder.getOrderId());
        Assertions.assertEquals(orderDTO, cancelledOrder, "Order should cancelled");
    }

    @Test
    @Transactional
    void updateOrder() {
        OrderDTO modifiedOrder = orderService.updateOrder(orderDTO);
        Assertions.assertEquals(orderDTO, modifiedOrder, "Modified order");
    }
}