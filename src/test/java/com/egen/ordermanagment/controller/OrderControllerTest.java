//package com.egen.ordermanagment.controller;
//
//import com.egen.ordermanagment.dto.OrdersDTO;
//import com.egen.ordermanagment.dtoModelMapper.OrderDTOMapper;
//import com.egen.ordermanagment.model.*;
//import com.egen.ordermanagment.repository.OrderRepository;
//import org.hamcrest.Matchers;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.sql.Timestamp;
//import java.util.*;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(
//        webEnvironment = SpringBootTest.WebEnvironment.MOCK
//)
//@AutoConfigureMockMvc
//@ActiveProfiles("integrationtest")
//public class OrderControllerTest {
//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    private OrderRepository repository;
//
//    private List<Orders> orders;
//    private List<OrdersDTO> ordersDTOS;
//    private OrderDTOMapper modelMapper = new OrderDTOMapper();
//
//    OrdersDTO orderDto;
//    Orders ord = new Orders();
//
//    @Before
//    public void setup(){
////        Orders ord = new Orders();
////        Address shippingAdr = new Address();
////        shippingAdr.setAddressId("Address test");
////        shippingAdr.setZip(89119);
////
////        ord.setOrderStatus(OrderStatus.PLACED);
////        ord.setId("orderId1");
////        ord.setCustomerId("customerId");
////        ord.setOrderSubTotal(10.0);
////        ord.setOrderTotal(20.0);
////        ord.setOrderTax(5.0);
////        ord.setShippingAddress(shippingAdr);
//
////        OrdersDTO ordersDTO = new OrdersDTO();
////        ordersDTO.setCustomerId(ord.getCustomerId());
////        ordersDTO.setOrderSubTotal(ord.getOrderSubTotal());
////        ordersDTO.setOrderTotal(ord.getOrderTotal());
////        ordersDTO.setOrderTax(ord.getOrderTax());
////        orders = Collections.singletonList(ord);
////        ordersDTOS = modelMapper.entityToDTO(orders);
//
//        List<OrderItems> itemsList = new ArrayList<>();
//        ord.setId("order1");
//        ord.setOrderStatus(OrderStatus.PLACED);
//        ord.setCustomerId("Customer1");
//        ord.setOrderCreated(Timestamp.valueOf("2021-06-22 12:00:09"));
//        ord.setOrderTotal(303.0);
//        ord.setOrderSubTotal(200.0);
//        ord.setOrderTax(10.0);
//
//        //Create item
//        OrderItems items = new OrderItems();
//        items.setOrderItemId("orderItem1");
//        items.setOrderItemName("Kellogs");
//        items.setOrderItemQty(10);
//        items.setOrderItemUnitPrice(1000.0);
//
//        itemsList.add(items);
//        ord.setOrderItemsList((List<OrderItems>) itemsList);
//
//
//        Address address = new Address();
//        address.setAddressId("address1");
//        address.setAddressLine1("4213 Grove Cir");
//        address.setAddressLine2("#7");
//        address.setCity("Las Vegas");
//        address.setState("NV");
//        address.setZip(89119);
//        ord.setShippingAddress(address);
//
//        //PaymentDetail
//        Payment payment = new Payment();
//        payment.setPaymentId("paymentId");
//        payment.setPaymentAmount(24.0);
//        payment.setPaymentMethod(PaymentMethod.CREDIT);
//        payment.setBillingAddress((Address) address);
//        payment.setPaymentDate(Timestamp.valueOf("2021-06-22 12:00:09"));
//        ord.setPaymentDetail(payment);
//
//        orders = Collections.singletonList(ord);
//        Mockito.when(repository.findAll()).thenReturn(orders);
//        Mockito.when(repository.findById(ord.getId()))
//                .thenReturn(Optional.of(ord));
//
//        orderDto = new OrdersDTO();
//        orderDto.setId("order1");
//        orderDto.setCustomerId("customer1");
//        orderDto.setOrderSubTotal(10.0);
//        orderDto.setOrderTotal(20.0);
//        orderDto.setOrderTax(5.0);
//        orderDto.setShippingAddress(address);
//        orderDto.setPaymentDetail(payment);
//        orderDto.setOrderItemsList((List<OrderItems>) itemsList);
//
//
//        Mockito.when(repository.findAllByOrderCreatedBetween(orders.get(0).getOrderCreated(),
//                orders.get(0).getOrderCreated())).thenReturn(orders);
//
////        Mockito.when(repository.findTop10OrdersWithHighestDollarAmountInZip(orders.get(0)
////        .getShippingAddress().getZip()).thenReturn(orderDto));
//
//        Mockito.when(repository.save(ord)).thenReturn(ord);
//
//    }
//
//    @After
//    public void cleanup(){
//        System.out.println("cleanup done");
//    }
//
//    @Test
//    public void getAllOrders() throws Exception{
//        mvc.perform(MockMvcRequestBuilders.get("/orders?page=0&size=5&sortBy=orderCreated"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.hasSize(1)));
//    }
//
//    @Test
//    public void getOrderById() throws Exception{
////        mvc.perform(MockMvcRequestBuilders.get("/employees/orderId1"))
////                .andExpect(MockMvcResultMatchers.status().isOk())
////                .andExpect(MockMvcResultMatchers.jsonPath("$.data.customerId", Matchers.is("customerId")));
//    }
//
//    @Test
//    public void getAllOrdersWithInInterval() {
//    }
//
//    @Test
//    public void top10OrdersWithHighestDollarAmountInZip() {
//    }
//
//    @Test
//    public void placeOrder() {
//    }
//
//    @Test
//    public void cancelOrder() {
//    }
//
//    @Test
//    public void updateOrder() {
//    }
//}