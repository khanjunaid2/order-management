package com.egen.service.impl;

import com.egen.entity.orders;
import com.egen.repository.IOrderRepository;
import com.egen.service.IOrdersService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
public class OrdersServiceTest {

    @TestConfiguration
    static class OrdersServiceTestConfiguration{
        @Bean
        public IOrdersService getService(){
            return new OrdersService();
        }
    }



    @Autowired
    private IOrdersService service;

    @MockBean
    private IOrderRepository repo;

    private List<orders> ordersList;
    @Before
    public void setup()
    {
//        orders ord=new orders();
//        ord.setCustomerName("Gowtham Mandava");
//        ord.setTotal(12334.34);
//        ord.setId("asdsa");
//
//       ordersList=Collections.singletonList(ord);
//
//        Mockito.when(repo.findAll()).thenReturn(ordersList);
//        Mockito.when(repo.findById(ord.getId())).thenReturn(Optional.of(ord));

    }

    @After
    public void cleanup()
    {

    }


    @Test
    public void getAllOrdersEmpty() {
//        List<orders> allOrders = service.getAllOrders();
//        Assert.assertEquals("Mismatch between returned and expected results", Collections.emptyList() ,allOrders );

    }
    @Test
    public void getAllOrders() {
//        List<orders> allOrders = service.getAllOrders();
//        Assert.assertEquals("Mismatch between returned and expected results", ordersList,allOrders );

    }

//@test(expected=ResourceNotFoundException.class)
    @Test
    public void getOrderById() {
//        orders result=service.getOrderById(ordersList.get(0).getId());
//        Assert.assertEquals("Mismatch between returned and expected results", ordersList.get(0),result );
  }

    @Test
    public void createOrder() {
    }

    @Test
    public void updateOrder() {
    }

    @Test
    public void delete() {
    }
}