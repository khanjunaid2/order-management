package com.egen.service;

import com.egen.model.Customer;
import com.egen.model.Order;
import com.egen.repository.OrderRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.util.List;

class OrderServiceTest {

    @TestConfiguration
    static class OrderServiceTestConfiguration {
        @Bean
        public OrderService getService() {
            return new OrderService();
        }
    }

    @Autowired
    OrderService orderService;

    @MockBean
    OrderRepository orderRepositoryImpl;

    @MockBean
    ItemsService itemsService;

    @MockBean
    PaymentService paymentService;

    @MockBean
    AddressService addressService;

    private List<Order> orderListMock;

    @Before
    public void setup(){
    }

    @Test
    void getAllOrders() {
    }

    @Test
    void getOrderById() {
    }

    @Test
    void top10OrdersWithHighestDollarAmountInZip() {
    }

    @Test
    void placeOrder() {
    }

    @Test
    void cancelOrder() {
    }

    @Test
    void updateOrder() {
    }
}