package com.egen.ordermanagment.services;

import com.egen.ordermanagment.dto.AddressDTO;
import com.egen.ordermanagment.dto.OrderItemsDTO;
import com.egen.ordermanagment.dto.OrdersDTO;
import com.egen.ordermanagment.dto.PaymentDTO;
import com.egen.ordermanagment.dtoModelMapper.OrderDTOMapper;
import com.egen.ordermanagment.exception.OrderServiceException;
import com.egen.ordermanagment.model.*;
import com.egen.ordermanagment.repository.OrderRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceImplTest {

    @MockBean
    private OrderRepository repository;

    @Autowired
    OrderService service;

    @Bean
    public OrderService getService() {
        return new OrderServiceImpl(repository);
    }

//    @Autowired
//    OrderDTOMapper orderDTOMapper;

    OrdersDTO orderDto = new OrdersDTO();
    Orders ord = new Orders();


    @Before
    public void setUp() {
        List<OrderItems> orderItemsList = new ArrayList<>();
        OrderItems orderItems = new OrderItems();
        orderItems.setOrderItemId("OrderItem1")
                .setOrderItemName("Kellogs")
                .setOrderItemQty(10)
                .setOrderItemUnitPrice(15.0);
        orderItemsList.add(orderItems);
        ord.setId("order1").setOrderStatus(OrderStatus.PLACED)
                .setCustomerId("Customer1")
                .setOrderCreated(Timestamp.valueOf("2021-06-22 12:00:09"))
                .setOrderModified(Timestamp.valueOf("2021-06-22 12:00:09"))
                .setOrderTotal(10.0)
                .setOrderSubTotal(10.0)
                .setOrderTax(5.0)
                .setOrderTotal(10.50)
                .setOrderItemsList(orderItemsList)
                .setPaymentDetail(new Payment().setPaymentId("paymentId1")
                        .setPaymentAmount(100.0)
                        .setPaymentMethod(PaymentMethod.CREDIT)
                        .setBillingAddress(new Address()
                                .setAddressId("Address6789")
                                .setAddressLine1("4217 Grove Cir")
                                .setAddressLine2("#5")
                                .setCity("Las Vegas")
                                .setState("NV")
                                .setZip("89119")))
                .setShippingAddress(new Address()
                        .setAddressId("Address2345")
                        .setAddressLine1("4217 Grove Cir")
                        .setAddressLine2("#5")
                        .setCity("Las Vegas")
                        .setState("NV")
                        .setZip("89119"));

        PaymentDTO paymentDTO = new PaymentDTO();
        List<OrderItemsDTO> orderItemsDTOList = new ArrayList<>();
        AddressDTO shippingAddressDTO = new AddressDTO();
        AddressDTO billingAddressDTO = new AddressDTO();

        for(int i=0; i< ord.getOrderItemsList().size(); i++){
            OrderItemsDTO orderItemsDTO = new OrderItemsDTO();
            BeanUtils.copyProperties(ord.getOrderItemsList().get(i) , orderItemsDTO);
            orderItemsDTOList.add(orderItemsDTO);
        }
        BeanUtils.copyProperties(ord.getPaymentDetail() , paymentDTO);
        BeanUtils.copyProperties(ord.getPaymentDetail().getBillingAddress(), billingAddressDTO);
        paymentDTO.setBillingAddress(billingAddressDTO);

        BeanUtils.copyProperties(ord.getShippingAddress(), shippingAddressDTO);
        orderDto.setId(ord.getId())
                .setCustomerId((ord.getCustomerId()))
                .setOrderSubTotal(ord.getOrderSubTotal())
                .setOrderTotal(ord.getOrderTotal())
                .setOrderTax(ord.getOrderTax())
                .setOrderCreated(ord.getOrderCreated())
                .setOrderModified(ord.getOrderModified())
                .setOrderStatus(ord.getOrderStatus())
                .setShipmentType(ord.getShipmentType())
                .setOrderItemsList(orderItemsDTOList)
                .setPaymentDetail(paymentDTO)
                .setShippingAddress(shippingAddressDTO);

        Mockito.when(repository.findAll()).thenReturn(Collections.singletonList(ord));

        Mockito.when(repository.findById(ord.getId())).thenReturn(Optional.of(ord));

        Mockito.when(repository.findAllByOrderCreatedBetween(ord.getOrderCreated(),
                ord.getOrderCreated())).thenReturn(Collections.singletonList(ord));

        Mockito.when(repository.findTop10OrdersWithHighestDollarAmountInZip(ord.getShippingAddress().getZip())).thenReturn(Collections.singletonList(ord));

        Mockito.when(repository.save(ord)).thenReturn(ord);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Clean up done");
    }

    @Test
    public void placeOrder() throws Exception {
        boolean create = service.placeOrder(orderDto);
        Assertions.assertEquals(create, true, "Order should be created");
    }

    @Test
    public void getAllOrders() throws Exception {
        List<Orders> result = service.getAllOrders();
        Assertions.assertEquals( Collections.singletonList(ord), result, "Orders should match");
    }

    @Test
    public void findAllPaginationSorting() throws Exception{
        List<OrdersDTO> result = service.findAllPaginationSorting(0,2,"orderCreated");
        Assertions.assertEquals(Collections.singletonList(orderDto), result, "Order list should match");
    }

    @Test
    public void findOne() throws Exception{
        OrdersDTO result = service.findOne(ord.getId());
        Assert.assertEquals("Order id should match", result, orderDto);
    }

    @Test(expected = OrderServiceException.class)
    public void findOneNotFound() {
        OrdersDTO result = service.findOne("test");
        Assert.assertEquals("Order id should not match", result, orderDto);
    }

    @Test
    public void findWithinInterval() throws Exception{
        List<OrdersDTO> ordersWithinTime = service.findWithinInterval(ord.getOrderCreated(),ord.getOrderCreated());
        Assertions.assertEquals(Collections.singletonList(orderDto), ordersWithinTime, "Orders should match");
    }

    @Test(expected = OrderServiceException.class)
    public void findWithinIntervalNotFound() {
        List<OrdersDTO> ordersWithinTime = service.findWithinInterval(Timestamp.valueOf("2019-06-22 12:00:22"), Timestamp.valueOf("2018-06-22 12:00:09"));
        Assertions.assertEquals(Collections.singletonList(orderDto), ordersWithinTime, "Orders should not match");
    }

    @Test
    public void findTop10OrdersWithHighestDollarAmountInZip() throws Exception{
        List<OrdersDTO> top10orders = service.findTop10OrdersWithHighestDollarAmountInZip(ord.getShippingAddress().getZip());
        Assertions.assertEquals(Collections.singletonList(orderDto), top10orders, "Orders should match");
    }

    @Test(expected = OrderServiceException.class)
    public void findTop10OrdersWithHighestDollarAmountInZipNotFound() {
        List<OrdersDTO> top10orders = service.findTop10OrdersWithHighestDollarAmountInZip("89765");
        Assertions.assertEquals(Collections.singletonList(orderDto), top10orders, "Orders should not match");
    }

    @Test
    public void updateOrder() {
        Boolean update = service.updateOrder(ord.getId(), orderDto);
        Assertions.assertEquals(update, true, "Order should be updated");
    }

    @Test
    public void cancelOrder() {
        Boolean cancel = service.cancelOrder(ord.getId());
        Assertions.assertEquals(cancel, true, "Order should be cancelled");
    }
}