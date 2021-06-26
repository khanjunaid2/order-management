package com.egen;

import com.egen.dto.OrderDTO;
import com.egen.enums.OrderStatus;
import com.egen.mapper.OrderMapper;
import com.egen.model.Order;
import com.egen.repository.OrderRepository;
import com.egen.service.OrderService;
import com.egen.service.OrderServiceImpl;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class OrderServiceImplTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderMapper orderMapper;
    @MockBean
    private OrderRepository repository;
    private List<Order> orders;
    private Order order;

    @Before
    public void setUp() {
        order = new Order();
        order.setId("KBP123");
        order.setOrderStatus(OrderStatus.PENDING);
        order.setTax(100.00);
        order.setTotalAmount(10000.00);

        orders = Collections.singletonList(order);

        Mockito.when(repository.findAll())
                .thenReturn(orders);

        Mockito.when(repository.findById(order.getId()))
                .thenReturn(Optional.of(order));
    }

    @After
    public void cleanUp() {
        System.out.println("Completed Tests");
    }

    @Test
    public void getAllOrders() {
        List<Order> result = orderMapper.toOrders(orderService.getAllOrders());
        Assert.assertEquals("Orders list should match", orders, result);
    }

    
    @Test
    public void getOrderById() {
        Order result = orderMapper.ToOrder((orderService.getOrderById("KBPA123")));
        Assert.assertEquals("Orders list should match", orders, result);
    }

    @Test
    public void placeOrder() {
        OrderDTO result = orderService.placeOrder(orderMapper.toOderDTO(order));
        Assert.assertEquals("Orders list should match", true, result);
    }

    @Test
    public void getAllOrdersWithInInterval() {
        List<Order> result = orderMapper.toOrders(orderService.getAllOrders());
        Assert.assertEquals("Orders list should match", orders, result);
    }

    @Test
    public void top10OrdersWithHighestDollarAmountInZip() {
        List<Order> result = orderMapper.toOrders(orderService.top10OrdersWithHighestDollarAmountInZip("94543"));
        Assert.assertEquals("Orders list should match", orders, result);
    }

    @Test
    public void cancelOrder() {
        OrderDTO result = orderService.cancelOrder(order.getId());
        Assert.assertEquals("Orders list should match", order, result);
    }

    @Test
    public void updateOrder() {
        Order orderDTO = orderMapper.toOderDTO(order);
        Order result = orderMapper.ToOrder(orderService.updateOrder(orderDTO.getId(), orderDTO));
        Assert.assertEquals("Orders list should match", orders, result);
    }

    @TestConfiguration
    static class OrderServiceImplTestConfiguration {
        @Bean
        public OrderService getService() {
            return new OrderServiceImpl();
        }
    }
}

