package com.egen.ordermanagement.service;

import com.egen.ordermanagement.exceptions.AddressServiceException;
import com.egen.ordermanagement.model.Address;
import com.egen.ordermanagement.repository.AddressRepo;
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

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceImplTest {
    @Bean
    public AddressService getService(){return new AddressServiceImpl();}

    @Autowired
    AddressService addressService;

    @MockBean
    AddressRepo addressRepo;
    Address address;
    @Before
    public void setUp(){
      address  = new Address();
        address.setId(9L).setAddressLine1("912 Carrington oak").setAddressLine2("#203")
                .setCity("Memphis").setState("TN").setZipcode("38125");

        Mockito.when(addressRepo.save(address)).thenReturn(address);
    }
    @After
    public void cleanUp(){System.out.println("Done testing Address Service");}

    @Test
    public void createAddress() {
        Address new_address = addressService.createAddress(address);
        Assert.assertEquals("Failed to create address",address,new_address);
    }
    @Test(expected = AddressServiceException.class)
    public void createAddressFailed() {
        Address new_address = addressService.createAddress(null);
    }
}