package com.egen.service;

import com.egen.dto.OrderDTO;
import com.egen.mapper.OrderMapper;
import com.egen.mapper.OrderMapperImpl;
import com.egen.model.entity.Orders;
import com.egen.model.enums.OrderStatus;
import com.egen.repository.OrderRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceImplTest {

    @Autowired
    private OrderService service;
    @Autowired
    private OrderMapper orderMapper;
    @MockBean
    private OrderRepository repository;
    private List<Orders> orders;
    private Orders order;

    @Before
    public void setUp() {
        order = new Orders();
        order.setOrderId("1234A");
        order.setOrderStatus(OrderStatus.PENDING);
        order.setCustomerId("Customer1");
        order.setTax(100.00);
        order.setTotal(10000.00);

        orders = Collections.singletonList(order);

        Mockito.when(repository.findAll())
                .thenReturn(orders);

        Mockito.when(repository.findById(order.getOrderId()))
                .thenReturn(Optional.of(order));
    }

    @After
    public void cleanUp() {
        System.out.println("Completed Tests");
    }

    @Test
    public void getAllOrders() {
        List<Orders> result = orderMapper.toOrders(service.getAllOrders());
        Assert.assertEquals("Orders list should match", orders, result);
    }

    @Test
    public void getOrderPage() {
        List<Orders> result = orderMapper.toOrders(service.getOrderPage(1));
        Assert.assertEquals("Orders list should match", orders, result);
    }

    @Test
    public void getOrderById() {
        Orders result = orderMapper.ToOrder((service.getOrderById("1234A")));
        Assert.assertEquals("Orders list should match", orders, result);
    }

    @Test
    public void placeOrder() {
        Boolean result = service.placeOrder(orderMapper.toOderDTO(order));
        Assert.assertEquals("Orders list should match", true, result);
    }

    @Test
    public void getAllOrdersWithInInterval() {
        List<Orders> result = orderMapper.toOrders(service.getAllOrders());
        Assert.assertEquals("Orders list should match", orders, result);
    }

    @Test
    public void top10OrdersWithHighestDollarAmountInZip() {
        List<Orders> result = orderMapper.toOrders(service.top10OrdersWithHighestDollarAmountInZip("12345"));
        Assert.assertEquals("Orders list should match", orders, result);
    }

    @Test
    public void cancelOrder() {
        OrderDTO result = service.cancelOrder(order.getOrderId());
        Assert.assertEquals("Orders list should match", order, result);
    }

    @Test
    public void updateOrder() {
        OrderDTO orderDTO = orderMapper.toOderDTO(order);
        Orders result = orderMapper.ToOrder(service.updateOrder(orderDTO.getOrderId(), orderDTO));
        Assert.assertEquals("Orders list should match", orders, result);
    }

    @TestConfiguration
    static class OrderServiceImplTestConfiguration {
        @Bean
        public OrderService getService() {
            return new OrderServiceImpl();
        }

        @Bean
        public OrderMapper getMapper() {
            return new OrderMapperImpl();
        }
    }
}