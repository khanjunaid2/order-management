package com.egen.ordermanagement.service;

import com.egen.ordermanagement.dto.CustomerDto;
import com.egen.ordermanagement.exceptions.CustomerServiceException;
import com.egen.ordermanagement.model.Address;
import com.egen.ordermanagement.model.Customer;
import com.egen.ordermanagement.repository.CustomerRepo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerServiceImplTest {

    @Bean
    public CustomerService getService(){return new CustomerServiceImpl();}

    @Autowired
    CustomerService customerService;

    @MockBean
    CustomerRepo customerRepo;
    Customer customer;
    CustomerDto customerDto;
    @Before
    public void setUp(){
        customer  = new Customer();
        customerDto = new CustomerDto();
      customer.setId(22L).setEmail("sandeep@egen.com").setFirstName("Sandeep").setLastName("Sagar");

      //Setting DTO values to entity to
      customerDto.setId(22L).setEmail("sandeep@egen.com").setFirstName("Sandeep").setLastName("Sagar");
      customer.setId(customerDto.getId()).setFirstName(customerDto.getFirstName())
                .setLastName(customerDto.getLastName());

        Mockito.when(customerRepo.findById(customer.getId())).thenReturn(Optional.of(customer));
        Mockito.when(customerRepo.save(customer)).thenReturn(customer);
        Mockito.when(customerRepo.save(customer)).thenReturn(customer);
    }
    @After
    public void cleanUp(){System.out.println("Done testing Address Service");}


    @Test
    public void findCustomer() {
        boolean isExisting = customerService.findCustomer(customer.getId());
        Assert.assertEquals("Customer doesnt exist",true,isExisting);
    }

    @Test(expected = CustomerServiceException.class)
    public void customerNotFound() {
        boolean isExisting = customerService.findCustomer(88L);
    }

    @Test
    public void createCustomer() {
        boolean new_customer = customerService.createCustomer(customerDto);
        Assert.assertEquals("Failed to create customer",true,new_customer);
    }
    @Test(expected = CustomerServiceException.class)
    public void createCustomerFailed() {
        boolean new_customer = customerService.createCustomer(customerDto);
    }
}