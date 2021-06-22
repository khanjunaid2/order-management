package com.example.ordermanagement.service;

import com.example.ordermanagement.DTO.OrdersDto;
import com.example.ordermanagement.enums.DeliveryMethod;
import com.example.ordermanagement.enums.OrderStatus;
import com.example.ordermanagement.enums.PaymentMethod;
import com.example.ordermanagement.mappers.OrdersMappers;
import com.example.ordermanagement.models.*;
import com.example.ordermanagement.repository.OrderRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
class OrderServiceImplementationTest {

    @Autowired
    private OrderService service;


    @MockBean
    private static OrderRepository repository;

    @TestConfiguration
    static class OrderServiceImplementationTestConfiguration{

        @Bean
        public OrderService getOrderService(){
            return new OrderServiceImplementation();
        }
    }



    List<Orders> ordersList;
    //OrdersDto orderDto;
    List<OrdersDto> ordersDtoList;
    Orders orders = new Orders();
    OrdersDto orderDto = new OrdersDto();


    OrdersMappers ordersMappers = new OrdersMappers();

    @Before
    public void mockData(){


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


        List<Payment> paymentList = new ArrayList<Payment>();
        Payment payment = new Payment();
        payment.setPaymentId("pay1");
        payment.setAmount(500.25);
        payment.setOrder_payment_date(Timestamp.valueOf("2021-06-22T05:42:37.214+00:00"));
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
        orders.setCreatedAt(Timestamp.valueOf("2021-06-22T05:42:37.214+00:00"));
        orders.setOrderModified(Timestamp.valueOf("2021-06-22T05:42:37.214+00:00"));
        orders.setCustomer(customer);

        ordersList = Collections.singletonList(orders);


        orderDto = ordersMappers.mapToDto(orders);
//        orderDto.setOrderId("order1");
//        orderDto.setOrderStatus(OrderStatus.PENDING);
//        orderDto.setSubTotal(500.2);
//        orderDto.setTax(3.00);
//        orderDto.setTotalAmount(503.20);
//        orderDto.setShippingAddress(address);
//        orderDto.setDeliveryMethod(DeliveryMethod.HOME_SHIPPING);
//        orderDto.setItems(itemsList);
//        orderDto.setPaymentDetails(paymentList);
//        orderDto.setCreatedAt(Timestamp.valueOf("2021-06-22T05:42:37.214+00:00"));
//        orderDto.setOrderModified(Timestamp.valueOf("2021-06-22T05:42:37.214+00:00"));
//        orderDto.setCustomer(customer);

        ordersDtoList = Collections.singletonList(orderDto);


        //Mockito functions
        Mockito.when(repository.findAll()).thenReturn(ordersList);

        Mockito.when(repository.save(orders)).thenReturn(orders);

    }

    @After
    public void cleanup(){
        System.out.println("Testing Over!!!!");
    }

    @Test
    void getAllOrders(){
        List<OrdersDto> result = service.getAllOrders();

        Assertions.assertEquals(ordersDtoList, result,"Orders List should Match");
    }

    @Test
    void getAllOrdersByPagingAndSorting(){
    }

    @Test
    void getOrderById(){
    }

    @Test
    void getAllOrdersWithInInterval(){
    }

    @Test
    void top10OrdersWithHighestDollarAmountInZip(){
    }

    @Test
    void placeOrder(){
//        OrdersDto ordersDto1 = service.placeOrder(orderDto);
//        Assertions.assertEquals(ordersDto1,ordersDto1);
    }

    @Test
    void cancelOrder(){
    }

    @Test
    void updateOrder(){
    }
}