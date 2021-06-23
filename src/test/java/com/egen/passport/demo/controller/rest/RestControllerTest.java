package com.egen.passport.demo.controller.rest;

import com.egen.passport.demo.dto.AddressDTO;
import com.egen.passport.demo.dto.OrderDTO;
import com.egen.passport.demo.entity.DeliveryType;
import com.egen.passport.demo.enums.AddressType;
import com.egen.passport.demo.enums.OrderStatus;
import com.egen.passport.demo.repository.OrderRepository;
import com.egen.passport.demo.response.Response;
import com.egen.passport.demo.service.OrderService;
import com.egen.passport.demo.service.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class RestControllerTest {

    @Mock
    private OrderService orderService;

    private CustomerController controller;

    @BeforeEach
    void setUp() {
        controller = new CustomerController(orderService);
    }




    @Test
    void testCreateOrder(){
        doReturn(true).when(orderService).createOrder(any());
        Response<String> res =  controller.createOrder(createOrderDto());
        Assertions.assertEquals(res.getData() , "Order Created");
    }




    OrderDTO createOrderDto(){
        OrderDTO order = new OrderDTO();
        order.setId(1);
        order.setOrderStatus(OrderStatus.PLACED);
        order.setTotalAmount(100);
        order.setCustomerId("1");
        order.setDeliveryType(DeliveryType.PICKUP);
        order.setTax(12);
        order.setSubTotal(120);
        AddressDTO address = new AddressDTO();
        address.setAddressLine1("9820 Spring Street");
        address.setCity("Chicago");
        address.setState("IL");
        address.setZip(60756);
        address.setAddressType(AddressType.SHIPPING);
        return order;
    }


}
