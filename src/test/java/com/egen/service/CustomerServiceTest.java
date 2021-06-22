package com.egen.service;

import com.egen.model.Customer;
import com.egen.repository.CustomerRepository;
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

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
class CustomerServiceTest {

    @TestConfiguration
    static class CustomerServiceTestConfiguration {
        @Bean
        public CustomerService getService() {
            return new CustomerService();
        }
    }

    @Autowired
    CustomerService customerService;

    @MockBean
    CustomerRepository customerRepositoryImpl;

    private List<Customer> customerListMock;

    @Before
    public void setup(){
        Customer customer1 =  new Customer();
        customer1.setId(1);
        customer1.setEmail("jsmith@gmail.com");
        customer1.setFirstName("John");
        customer1.setLastName("Smith");
        customer1.setPhoneNumber("7264936782");

        Customer customer2 =  new Customer();
        customer1.setId(2);
        customer2.setEmail("jdoe@gmail.com");
        customer2.setFirstName("Jane");
        customer2.setLastName("Doe");
        customer2.setPhoneNumber("6730284629");

        customerListMock.add(customer1);
        customerListMock.add(customer2);
        System.out.println("Cust "+customerListMock.toString());
        Mockito.when(customerRepositoryImpl.findAll())
                .thenReturn(customerListMock);

        Mockito.when(customerRepositoryImpl.findById(customerListMock.get(0).getId())).
                thenReturn(Optional.of(customerListMock.get(0)));
    }


    @Test
    void findAll() {
        List<Customer> customerList = customerService.findAll();
        Assert.assertEquals("Customer list should match", customerListMock,customerList);
    }


    @Test
    void getById() {
        Customer cust=customerService.getById(1);
        Assert.assertEquals("Customer should match", customerListMock.get(0),cust);
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}