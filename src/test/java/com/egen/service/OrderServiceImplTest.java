package com.egen.service;

import com.egen.entity.Order;
import com.egen.enums.OrderStatus;
import com.egen.exception.OrderNotFoundException;
import com.egen.repository.OrderRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
public class OrderServiceImplTest {

    @TestConfiguration
    static class OrderServiceImplTestConfiguration {
        @Bean
        public OrderService getOrderService() {
            return new OrderServiceImpl();
        }
    }

    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderRepository orderRepository;

    private List<Order> orders;

    @Before
    public void setup() {
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setStatus(OrderStatus.SUBMITTED);
        order.setTotal(100.39);

        orders = Collections.singletonList(order);

        Mockito.when(orderRepository.findAll())
                .thenReturn(orders);

        Mockito.when(orderRepository.findById(order.getId()))
                .thenReturn(Optional.of(order));
    }

    @After
    public void cleanup() {
        System.out.println("after");
    }

    @Test
    public void getAllOrders() {
        List<Order> result = orderService.getAllOrders();
        Assert.assertEquals("Order list should match", orders, result);
    }

    @Test
    public void getOrderById() {
        Order result = orderService.getOrderById(orders.get(0).getId());
        Assert.assertEquals("Order should match", orders.get(0), result);
    }

    @Test(expected = OrderNotFoundException.class)
    public void getOrderByIdNotFound() throws Exception {
        Order result = orderService.getOrderById("id");
        Assert.assertEquals("Order should not match", orders.get(0), result);
    }

    @Test
    public void getAllOrdersWithinInterval() {
    }

    @Test
    public void top10OrdersWithHighestDollarAmountInZip() {
    }

    @Test
    public void placeOrder() {
    }

    @Test
    public void cancelOrder() {
    }

    @Test
    public void updateOrder() {
    }
}