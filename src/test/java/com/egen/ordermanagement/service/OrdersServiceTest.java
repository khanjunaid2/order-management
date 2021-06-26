package com.egen.ordermanagement.service;

import com.egen.ordermanagement.dto.OrderDto;
import com.egen.ordermanagement.enums.OrderStatus;
import com.egen.ordermanagement.enums.PaymentMethod;
import com.egen.ordermanagement.enums.ShipmentMethod;
import com.egen.ordermanagement.exceptions.OrderServiceException;
import com.egen.ordermanagement.model.Address;
import com.egen.ordermanagement.model.Item;
import com.egen.ordermanagement.model.Orders;
import com.egen.ordermanagement.model.Payment;
import com.egen.ordermanagement.repository.ItemRepo;
import com.egen.ordermanagement.repository.OrdersRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrdersServiceTest {

  //@Mock

    OrdersRepo ordersRepo;
    AddressService addressService;
    CustomerService customerService;
    ItemService itemService;
    PaymentService paymentService;
    OrdersService ordersService;

    @Before
    public void init_mocks() {
        itemService = Mockito.mock(ItemServiceImpl.class);
        addressService = Mockito.mock(AddressService.class);
        ordersRepo = Mockito.mock(OrdersRepo.class);
        paymentService = Mockito.mock(PaymentService.class);
        customerService = Mockito.mock(CustomerService.class);
        ordersService = new OrdersServiceImpl(ordersRepo,itemService,customerService,paymentService,addressService);
    }

    @Test
    public void testCreateOrder(){

        Boolean create = this.ordersService.createOrder(createOrderDto());
    //    when(ordersService.createOrder(createOrderDto())).thenThrow(new OrderServiceException());
    //    when(this.ordersService.createOrder(createOrderDto())).thenReturn(true);
        Assertions.assertTrue(ordersService.createOrder((createOrderDto())));
        Assertions.assertTrue(create);
    }
    @Test
    public void testCreateOrderById(){
        doReturn(createOrderData()).when(ordersService.findOne(8L));
        Assert.assertEquals("ID dint match", java.util.Optional.ofNullable(createOrderData().getId()),8L);
    }

  public Orders createOrderData(){
        Item items = new Item();
        items.setId(8L).setQuantityInStock(3).setItemName("Energy Drink").setItemPrice(74.5);
        Set<Item> itemSet = new HashSet<>();
        itemSet.add(items);

        Set<Payment> paymentSet = new HashSet<>();

        Address address = new Address();
        address.setId(9L).setAddressLine1("912 Carrington oak").setAddressLine2("#203")
                .setCity("Memphis").setState("TN").setZipcode("38125");
        Payment payments = new Payment();
        payments.setId(10L).setAmount(3.0).setPaymentMode(PaymentMethod.CHECKING_ACCOUNT)
                .setBillingAddress(address).setPaymentDate(Timestamp.valueOf("2021-06-18 18:33:09"));
        paymentSet.add(payments);
        return Orders.builder().id(8L).shippingAddress(address).subTotal(20.0)
                .tax(1.0).total(21.0).shipmentMethod(ShipmentMethod.HOME_DELIVERY).orderStatus(OrderStatus.PLACED)
                .shippingCharges(1.0).items(itemSet).paymentDetails(paymentSet).build();
    }
   public OrderDto createOrderDto(){
        OrderDto orderDto = new OrderDto();
        Address address = new Address();
        address.setId(9L).setAddressLine1("912 Carrington oak").setAddressLine2("#203")
                .setCity("Memphis").setState("TN").setZipcode("38125");
        Payment payments = new Payment();
        payments.setId(10L).setAmount(3.0).setPaymentMode(PaymentMethod.CHECKING_ACCOUNT)
                .setBillingAddress(address).setPaymentDate(Timestamp.valueOf("2021-06-18 18:33:09"));
        List<Payment> paymentList = new ArrayList<>();

        orderDto.setId(8L);
        orderDto.setOrderStatus(OrderStatus.PLACED);
        orderDto.setShippingAddress(address);
        orderDto.setBillingSameAsShippingAddress(true);
        orderDto.setItemQuantity(2);
     //   orderDto.setItems(new long[]{8});
        orderDto.setPayments(paymentList);
        orderDto.setShipmentMethod(ShipmentMethod.HOME_DELIVERY);
        return orderDto;
    }
}