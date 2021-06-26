package com.example.ordermanagement.service;

import com.example.ordermanagement.DTO.OrdersDto;
import com.example.ordermanagement.enums.DeliveryMethod;
import com.example.ordermanagement.enums.OrderStatus;
import com.example.ordermanagement.enums.PaymentMethod;
import com.example.ordermanagement.mappers.OrdersMappers;
import com.example.ordermanagement.models.*;
import com.example.ordermanagement.repository.OrderRepository;
import com.example.ordermanagement.service.orders.OrderService;
import com.example.ordermanagement.service.orders.OrderServiceImplementation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
class OrderServiceImplementationTest {

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    OrderService orderService;


    @Autowired
    OrdersMappers ordersMappers;

//    @TestConfiguration
//    static class OrderServiceImplementationTestConfig{

        @Bean
        public OrderService getOrderService(){
            return new OrderServiceImplementation();
        }
    //}



    Orders orders = new Orders();
    OrdersDto ordersDto = new OrdersDto();

    @BeforeEach
    public void setUp(){
        //System.out.println("Hello");
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

        Mockito.when(orderRepository.findAll())
                .thenReturn(Collections.singletonList(orders));
        Mockito.when(orderRepository.findById(orders.getOrderId()))
                .thenReturn(Optional.of(orders));
        Mockito.when(orderRepository.save(orders))
                .thenReturn(orders);

    }

    @AfterEach
    public void cleanup(){ }

    @Test
    void getAllOrders() {
        List<OrdersDto> result = orderService.getAllOrders();
        Assertions.assertEquals(result, Collections.singletonList(ordersDto), "Order list matches");
    }

    @Test
    void getAllOrdersByPagingAndSorting() {

    }

    @Test
    void getOrderById() {
        OrdersDto dto = orderService.getOrderById(orders.getOrderId());
        Assertions.assertEquals(dto,ordersDto);
    }

//    @Test
//    void getAllOrdersWithInInterval() {
//    }
//
//    @Test
//    void top10OrdersWithHighestDollarAmountInZip() {
//    }

    @Test
    void placeOrder() {
        OrdersDto dto = orderService.placeOrder(ordersDto);
        //System.out.println(dto);
        Assertions.assertEquals(dto,ordersDto);
    }

    @Test
    void cancelOrder() {
        OrdersDto dto = orderService.cancelOrder(orders.getOrderId());

        //Check whether both are unequal because dto orderStatus will be CANCELED whereas ordersDto will be PENDING
        Assertions.assertNotEquals(dto.getOrderStatus(), ordersDto.getOrderStatus());
        //Assertions.assertEquals(dto, ordersDto);
    }

    @Test
    void updateOrder() {
        OrdersDto dto = orderService.updateOrder(orders, orders.getOrderId());

        Assertions.assertNotEquals(dto.getOrderStatus(), ordersDto.getOrderStatus());
    }
}