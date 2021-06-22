package com.egen.ordermanagement.service;

import com.egen.ordermanagement.enums.PaymentMethod;
import com.egen.ordermanagement.exceptions.PaymentServiceException;
import com.egen.ordermanagement.model.Address;
import com.egen.ordermanagement.model.Orders;
import com.egen.ordermanagement.model.Payment;
import com.egen.ordermanagement.repository.AddressRepo;
import com.egen.ordermanagement.repository.PaymentRepo;
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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PaymentServiceImplTest {

    @Bean
    public PaymentService getService(){return new PaymentServiceImpl();}

    @Autowired
    PaymentService paymentService;

    @MockBean
    PaymentRepo paymentRepo;

    Payment payment;
    List<Payment> paymentList;
    Address addresses;
    Orders new_order;

    @Before
    public void setUp(){
        payment = new Payment();
        new_order = new Orders();
        addresses = new Address();
        paymentList = new ArrayList<>();
        addresses.setId(9L);
        new_order.setId(8L);

        payment.setId(10L).setAmount(3.0).setPaymentMode(PaymentMethod.CHECKING_ACCOUNT)
                .setBillingAddress(addresses).setPaymentDate(Timestamp.valueOf("2021-06-18 18:33:09"))
                .setOrders(new_order);

        paymentList.add(payment);
        payment.setId(11L).setAmount(20.0).setPaymentMode(PaymentMethod.PAYPAL)
                .setBillingAddress(addresses).setPaymentDate(Timestamp.valueOf("2021-06-18 18:33:09"))
                .setOrders(new_order);
        paymentList.add(payment);

        Mockito.when(paymentRepo.save(payment)).thenReturn(payment);
    }
    @After
    public void cleanUp(){
        System.out.println("Done testing Payment Service");
    }

    @Test
    public void createPayment() {
      List<Payment> new_payments= paymentService.createPayment(paymentList,addresses,new_order);
        Assert.assertEquals("failed to make payment",paymentList,new_payments);
    }
    @Test(expected = PaymentServiceException.class)
    public void createPaymentFailed() {
        List<Payment> new_payments= paymentService.createPayment(paymentList,addresses,new_order);
        Assert.assertEquals("failed to make payment",null,new_payments);
    }
}