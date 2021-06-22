package com.egen.ordermanagment.services;

import com.egen.ordermanagment.dto.OrdersDTO;
import com.egen.ordermanagment.dtoModelMapper.OrderDTOMapper;
import com.egen.ordermanagment.exception.OrderServiceException;
import com.egen.ordermanagment.model.*;
import com.egen.ordermanagment.repository.OrderRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mockStatic;

@RunWith(SpringRunner.class)
public class OrderServiceImplTest {

    @TestConfiguration
    static class OrderServiceImplTestConfiguration {
        @Bean
        public OrderService getService(){
            return new OrderServiceImpl();
        }
    }

    @Autowired
    OrderService service;

    @MockBean
    OrderRepository repository;

    private List<Orders> orders;
    private List<OrdersDTO> ordersDTOS;
    private OrderDTOMapper modelMapper = new OrderDTOMapper();

    OrdersDTO orderDto;
    Orders ord = new Orders();

    @Before
    public void setup(){
//        Orders ord = new Orders();
//        Address shippingAdr = new Address();
//        shippingAdr.setAddressId("Address test");
//        shippingAdr.setZip(89119);
//
//        ord.setOrderStatus(OrderStatus.PLACED);
//        ord.setId("orderId1");
//        ord.setCustomerId("customerId");
//        ord.setOrderSubTotal(10.0);
//        ord.setOrderTotal(20.0);
//        ord.setOrderTax(5.0);
//        ord.setShippingAddress(shippingAdr);

//        OrdersDTO ordersDTO = new OrdersDTO();
//        ordersDTO.setCustomerId(ord.getCustomerId());
//        ordersDTO.setOrderSubTotal(ord.getOrderSubTotal());
//        ordersDTO.setOrderTotal(ord.getOrderTotal());
//        ordersDTO.setOrderTax(ord.getOrderTax());
//        orders = Collections.singletonList(ord);
//        ordersDTOS = modelMapper.entityToDTO(orders);

        List<OrderItems> itemsList = new ArrayList<>();
        ord.setId("order1");
        ord.setOrderStatus(OrderStatus.PLACED);
        ord.setCustomerId("Customer1");
        ord.setOrderCreated(Timestamp.valueOf("2021-06-22 12:00:09"));
        ord.setOrderTotal(303.0);
        ord.setOrderSubTotal(200.0);
        ord.setOrderTax(10.0);

        //Create item
        OrderItems items = new OrderItems();
        items.setOrderItemId("orderItem1");
        items.setOrderItemName("Kellogs");
        items.setOrderItemQty(10);
        items.setOrderItemUnitPrice(1000.0);

        itemsList.add(items);
        ord.setOrderItemsList((List<OrderItems>) itemsList);

        Address address = new Address();
        address.setAddressId("address1");
        address.setAddressLine1("4213 Grove Cir");
        address.setAddressLine2("#7");
        address.setCity("Las Vegas");
        address.setState("NV");
        address.setZip(89119);
        ord.setShippingAddress(address);

        //PaymentDetail
        Payment payment = new Payment();
        payment.setPaymentId("paymentId");
        payment.setPaymentAmount(24.0);
        payment.setPaymentMethod(PaymentMethod.CREDIT);
        payment.setBillingAddress((Address) address);
        payment.setPaymentDate(Timestamp.valueOf("2021-06-22 12:00:09"));
        ord.setPaymentDetail(payment);

        orders = Collections.singletonList(ord);
        Mockito.when(repository.findAll()).thenReturn(orders);
        Mockito.when(repository.findById(ord.getId()))
                .thenReturn(Optional.of(ord));

        orderDto = new OrdersDTO();
        orderDto.setId("order1");
        orderDto.setCustomerId("customer1");
        orderDto.setOrderSubTotal(10.0);
        orderDto.setOrderTotal(20.0);
        orderDto.setOrderTax(5.0);
        orderDto.setShippingAddress(address);
        orderDto.setPaymentDetail(payment);
        orderDto.setOrderItemsList((List<OrderItems>) itemsList);


        Mockito.when(repository.findAllByOrderCreatedBetween(orders.get(0).getOrderCreated(),
                orders.get(0).getOrderCreated())).thenReturn(orders);

//        Mockito.when(repository.findTop10OrdersWithHighestDollarAmountInZip(orders.get(0)
//        .getShippingAddress().getZip()).thenReturn(orderDto));

        Mockito.when(repository.save(ord)).thenReturn(ord);

    }

    @After
    public void cleanup(){
        System.out.println("cleanup done");
    }

    @Test
    public void getAllOrders() throws Exception{
        List<Orders> result = service.getAllOrders();
        Assert.assertEquals("orders should match", result, result);
    }

    @Test
    public void findOne() throws Exception{
        OrdersDTO result = service.findOne(orders.get(0).getId());
        Assert.assertEquals("order id should match", result, result);
    }

    @Test(expected = OrderServiceException.class)
    public void findOneNotFound() throws Exception{
        OrdersDTO result = service.findOne("test");
    }

    @Test
    public void findWithinInterval() throws Exception{
        List<Orders> ordersWithinTime = service.findWithinInterval(orders.get(0).getOrderCreated(),
                orders.get(0).getOrderCreated());
        Assert.assertEquals("orders not matched",ordersWithinTime,orders);
    }

    @Test(expected = OrderServiceException.class)
    public void findWithinIntervalNotFound() {
        Orders ord= new Orders();
        List<Orders> ordersWithinTime = service.findWithinInterval(ord.getOrderCreated(),
                ord.getOrderCreated());
    }

    @Test
    public void findTop10OrdersWithHighestDollarAmountInZip() throws Exception{
//        List<OrdersDTO> top10orders = service.findTop10OrdersWithHighestDollarAmountInZip(orders.get(0)
//                .getShippingAddress().getZip());
//        Assert.assertEquals("Orders not matched",orders,top10orders);
    }

    @Test
    public void placeOrder() throws Exception{
        OrdersDTO order = service.placeOrder(orderDto);
        Assert.assertEquals("Error while creating order",order,order);
    }

    @Test
    public void cancelOrder() throws Exception{
        OrdersDTO cancel = service.cancelOrder(orders.get(0).getId());
        Assert.assertEquals("Error while updating order as cancelled","CANCELLED",cancel.getOrderStatus().toString());
    }
}