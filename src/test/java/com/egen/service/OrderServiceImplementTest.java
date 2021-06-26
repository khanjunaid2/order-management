package com.egen.service;

import com.egen.controller.OrderController;
import com.egen.dto.CustomerDTO;
import com.egen.dto.OrderItemDTO;
import com.egen.model.*;
import com.egen.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplementTest {

    @Mock
    private OrderServiceImplement orderServiceImplement;
    private OrderController orderController;
    @BeforeEach
    void setUp() {
        orderController = new OrderController(orderServiceImplement);


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void placeOrder() {
        doReturn(true).when(orderServiceImplement).placeOrder(any());

        Response<OrderItemDTO> res = orderController.placeOrder(createMockOrder());
        Assertions.assertEquals(res.getData(),"Order Placed");

    }

    @Test
    void getAllOrders() {
    }

    OrderItemDTO createMockOrder(){
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        Customer  customer  = new Customer ();
        Item item = new Item();
        Payment payment = new Payment();
        Address address =  new Address();

        address.setId("address1");
        address.setAddLine1("1400");
        address.setCity("Buffalo");
        address.setState("Ny");
        address.setZipcode("14221");


        customer.setId("charan");
        customer.setFirstName("Charan");
        customer.setLastName("kalva");
        customer.setEmail("charan@buffalo.edu");
        customer.setPhoneNo("7169397858");

        item.setId("Item1");
        item.setItemName("ItemName");
        item.setItemPrice(10);
        item.setQuantity(5);

        payment.setId("payment1");
        payment.setPaymentType(PaymentType.PAYPAL);
        payment.setAmountPaid(10);
        payment.setBillingAddress(address);


        orderItemDTO.setOrderId("charan");
        orderItemDTO.setId("jnkasdbkja");
        orderItemDTO.setCustomer(customer );
        Set<Item> items = new HashSet<>();
        items.add(item);
        orderItemDTO.setItems(items);
        orderItemDTO.setOrderStatus(OrderStatus.CREATED);
        orderItemDTO.setDeliverytype(DeliveryType.INSTORE_PICKUP);
        Set<Payment> payments = new HashSet<>();
        payments.add(payment);
        orderItemDTO.setPayments(payments);
        orderItemDTO.setShippingAddress(address);
        orderItemDTO.setSubTotal(100);
        orderItemDTO.setTax(0.55);
        orderItemDTO.setTotalAmount(150);
        return orderItemDTO;
    }
}