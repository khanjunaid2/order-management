package com.egen.passport.demo.service;

import com.egen.passport.demo.dto.AddressDTO;
import com.egen.passport.demo.dto.OrderDTO;
import com.egen.passport.demo.entity.Address;
import com.egen.passport.demo.entity.CustomerOrder;
import com.egen.passport.demo.entity.DeliveryType;
import com.egen.passport.demo.enums.AddressType;
import com.egen.passport.demo.enums.OrderStatus;
import com.egen.passport.demo.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {


    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    OrderService orderService = new OrderServiceImpl(orderRepository);

    /*@Mock
    OrderService orderService;*/


    @Test
    void testCreateOrder(){
        doReturn(true).when(orderService).createOrder(any());
        Assertions.assertTrue(orderService.createOrder(createOrderDto()));
    }


    @Test
    void testOrderById(){
        doReturn(createOrderData()).when(orderService).getOrderById(1L);
        CustomerOrder order = orderService.getOrderById(1L);
        Assertions.assertEquals("1" , order.getCustomerId());
        Assertions.assertEquals(OrderStatus.PLACED, order.getOrderStatus());
    }

    @Test
    void testOrderById2(){
        doReturn(createOrderData()).when(orderRepository).findCustomerOrderById(1L);
        CustomerOrder order = orderService.getOrderById(1L);
        Assertions.assertEquals("1" , order.getCustomerId());
        Assertions.assertEquals(OrderStatus.PLACED, order.getOrderStatus());
    }


    CustomerOrder createOrderData(){

        List<Address> addressList = new ArrayList<Address>();
        addressList.add(Address.builder().
                addressLine1("9820 Spring Street").city("Chicago").state("IL").zip(60426).build());

        return CustomerOrder.builder().id(1).orderStatus(OrderStatus.PLACED).totalAmount(130).
                customerId("1").deliveryType(DeliveryType.PICKUP).
                tax(12).subTotal(120).addresses(addressList).build();
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
