package com.egen.ordermanagement.service;

import com.egen.ordermanagement.entity.Order;
import com.egen.ordermanagement.entity.OrderStatus;
import com.egen.ordermanagement.repository.OrderRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
class OrderServiceImplTest {

    @TestConfiguration
    static class OrderServiceImplTestConfiguration{

        @Bean
        public OrderService getService() {
            return new OrderServiceImpl();
        }
    }

    @Autowired
    private OrderService service;

    @MockBean
    private OrderRepository repository;

    private List<Order> orders;

    @Before
    public void setUp(){
        Order ord = new Order();
        ord.setCustomerId("1");
        ord.getOrderStatus(OrderStatus.ORDERED);

        orders = Collections.singletonList(ord);

        Mockito.when(repository.findAll())
                .thenReturn(orders);
        Mockito.when(repository.findById(ord.getId()))
                .thenReturn(Optional.of(ord));
    }

    @After
    public void cleanUp(){
        System.out.println("cleanUp");
    }

    @Test
    void getAllOrders() {
        if(service!=null) {
            List<Order> result = service.getAllOrders();
            Assert.assertEquals("Orders should match", orders, result);
        }
     }

    @Test
    void testGetAllOrders() {
    }

    @Test
    void getOrderById() {
        if(service!=null) {
            Order ord = service.getOrderById(orders.get(0).getId());
            Assert.assertEquals("Order Should match", orders.get(0), ord);
        }
    }

    @Test
    void getAllOrdersWithinInterval() {
    }

    @Test
    void getTop10OrdersWithHighestDollarAmountInZip() {
    }

    @Test
    void placeOrder() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}