package com.example.ordermanagement.controller;

import com.example.ordermanagement.DTO.OrdersDto;
import com.example.ordermanagement.enums.DeliveryMethod;
import com.example.ordermanagement.enums.OrderStatus;
import com.example.ordermanagement.enums.PaymentMethod;
import com.example.ordermanagement.mappers.OrdersMappers;
import com.example.ordermanagement.models.*;
import com.example.ordermanagement.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc //works as postman client
@ActiveProfiles("integrationtest") //gets the hibernate application properties
public class OrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    OrdersMappers ordersMappers;

    Orders orders = new Orders();
    OrdersDto ordersDto = new OrdersDto();

    @Before
    public void setUp(){

        Address address = new Address();
        address.setAddressId("addr1");
        address.setAddressLine1("660 S Laflin St");
        address.setAddressLine2("Circle Apt");
        address.setCity("Chicago");
        address.setState("Illinois");
        address.setZip("60607");

        List<Item> itemsList = new ArrayList<>();
        Item item = new Item();
        item.setOrderItemId("item1");
        item.setItemName("Iphone8");
        item.setPrice(500.25);
        item.setQuantity(2);

        itemsList.add(item);


        List<Payment> paymentList = new ArrayList<>();
        Payment payment = new Payment();
        payment.setPaymentId("pay1");
        payment.setAmount(500.25);
        payment.setOrder_payment_date(Timestamp.valueOf("2021-06-22 05:42:37"));
        payment.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        payment.setBillingAddress(address);

        paymentList.add(payment);


        Customer customer = new Customer();
        customer.setCustomerId("cust1");
        customer.setFirstName("Michael");
        customer.setLastName("Bevan");
        customer.setEmail("mbevan@gmail.com");

        orders.setOrderId("order1");
        orders.setOrderStatus(OrderStatus.PENDING);
        orders.setSubTotal(500.2);
        orders.setTax(3.00);
        orders.setTotalAmount(503.20);
        orders.setShippingAddress(address);
        orders.setDeliveryMethod(DeliveryMethod.HOME_SHIPPING);
        orders.setItems(itemsList);
        orders.setPaymentDetails(paymentList);
        orders.setCreatedAt(Timestamp.valueOf("2021-06-22 05:42:37"));
        orders.setOrderModified(Timestamp.valueOf("2021-06-22 05:42:37"));
        orders.setCustomer(customer);

        ordersDto = ordersMappers.mapToDto(orders);
        //BeanUtils.copyProperties(orders, ordersDto);

        orderRepository.save(orders);

    }
    
    @After
    public void cleanup(){
        orderRepository.deleteAll();
    }

    @Test
    public void getAllOrders() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/orders"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].orderId", Matchers.is("order1")));
    }


    @Test
    public void getOrderById() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/orders/order1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderId", Matchers.is("order1")));
    }

    @Test
    public void getOrderById404() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/orders/order12"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void placeOrder() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        Orders orders = new Orders();

        Address address = new Address();
        address.setAddressId("addr2");
        address.setAddressLine1("660 S Laflin St");
        address.setAddressLine2("Circle Apt");
        address.setCity("Chicago");
        address.setState("Illinois");
        address.setZip("60607");

        List<Item> itemsList = new ArrayList<>();
        Item item = new Item();
        item.setOrderItemId("item2");
        item.setItemName("Iphone8");
        item.setPrice(500.25);
        item.setQuantity(2);

        itemsList.add(item);


        List<Payment> paymentList = new ArrayList<>();
        Payment payment = new Payment();
        payment.setPaymentId("pay2");
        payment.setAmount(500.25);
        payment.setOrder_payment_date(Timestamp.valueOf("2021-06-22 05:42:37"));
        payment.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        payment.setBillingAddress(address);

        paymentList.add(payment);


        Customer customer = new Customer();
        customer.setCustomerId("cust2");
        customer.setFirstName("Michael");
        customer.setLastName("Bevan");
        customer.setEmail("mbevan@gmail.com");

        orders.setOrderId("order2");
        orders.setOrderStatus(OrderStatus.PENDING);
        orders.setSubTotal(500.2);
        orders.setTax(3.00);
        orders.setTotalAmount(503.20);
        orders.setShippingAddress(address);
        orders.setDeliveryMethod(DeliveryMethod.HOME_SHIPPING);
        orders.setItems(itemsList);
        orders.setPaymentDetails(paymentList);
        orders.setCreatedAt(Timestamp.valueOf("2021-06-22 05:42:37"));
        orders.setOrderModified(Timestamp.valueOf("2021-06-22 05:42:37"));
        orders.setCustomer(customer);



        mvc.perform(MockMvcRequestBuilders.post("/placeorder")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content(mapper.writeValueAsBytes(orders))
                                            .characterEncoding("utf-8")

        );
                //.andExpect(MockMvcResultMatchers.status().isOk())
                //.andExpect(MockMvcResultMatchers.jsonPath("$.orderId", Matchers.notNullValue()));
    }

    @Test
    public void cancelOrder() throws Exception{
    }

    @Test
    public void updateOrder() throws Exception{
    }
}