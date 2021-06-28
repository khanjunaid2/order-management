package com.egen.ordermanagement.service.order;

import com.egen.ordermanagement.dto.OrderDTO;
import com.egen.ordermanagement.mapper.CircularMappingResolver;
import com.egen.ordermanagement.mapper.OrderMapper;
import com.egen.ordermanagement.model.entity.*;
import com.egen.ordermanagement.model.enums.OrderStatus;
import com.egen.ordermanagement.model.enums.PaymentType;
import com.egen.ordermanagement.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    private CustomerOrder customerOrder;
    private OrderDTO orderDTO;

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderService orderService = new OrderServiceImpl(orderRepository);

    @BeforeEach
    public void createMockData() {

        customerOrder = new CustomerOrder();
        orderDTO = new OrderDTO();

        orderDTO = OrderMapper.MAPPER.orderToOrderDTO(getOrderMockObject(customerOrder), new CircularMappingResolver());
        createRepositoryMocks(customerOrder);
    }

    @AfterEach
    public void cleanUp() {}

    @Test
    void getAllOrders() {
        Mockito.when(orderRepository.findAll()).thenReturn(Collections.singletonList(customerOrder));
        Assertions.assertEquals(orderDTO, orderService.getAllOrders().get(0));
    }

    @Test
    void getAllOrdersWithPaginationAndSorted() {
    }

    @Test
    void getOrderById() {
        Mockito.when(orderRepository.findById(customerOrder.getOrderId())).thenReturn(Optional.of(customerOrder));
    }

    @Test
    void getAllOrdersWithInInterval() {

        Mockito.when(orderRepository.findAllOrdersWithInInterval(Timestamp.valueOf("2021-06-22 23:53:38"),
                Timestamp.valueOf("2021-06-24 23:53:38"))).thenReturn(Collections.singletonList(customerOrder));
    }

    @Test
    void top10OrdersWithHighestDollarAmountInZip() {
    }

    @Test
    void placeOrder() {

        Mockito.when(orderRepository.save(customerOrder)).thenReturn(customerOrder);
    }

    @Test
    void cancelOrder() {
    }

    @Test
    void updateOrder() {
    }

    @Test
    void updateOrderStatus() {
    }

    private CustomerOrder getOrderMockObject(CustomerOrder customerOrder) {

        customerOrder.setOrderId("testOrder1")
                .setCreationDate(Timestamp.valueOf("2021-06-22 23:53:38"))
                .setModificationDate(Timestamp.valueOf("2021-06-22 23:53:38"))
                .setCustomerId("customer1")
                .setTotal(250.50)
                .setSubtotal(235.20)
                .setTax(14.50)
                .setStatus(OrderStatus.PENDING);

        customerOrder.setItems(Collections.singletonList(new Item().setItemId("itemId1").setItemName("item1").setItemQuantity(4).setItemUnitPrice(12.50).setOrders(customerOrder)))
                .setPayments(Collections.singletonList(new Payment().setPaymentId("paymentId1").setAmount(250.50).setPaymentType(PaymentType.CREDIT).setOrders(customerOrder)
                        .setBilling(new Billing().setBillingId("billingId1").setAddressLine1("river st").setAddressLine2("line2").setCity("deplanes").setState("Illinois").setZip(60016))))
                .setShipping(new Shipping().setShippingId("shippingId1").setAddressLine1("line1").setAddressLine2("line2").setCity("chicago").setState("Illinois").setZip("60016"));

        return customerOrder;
    }

    private void createRepositoryMocks(CustomerOrder customerOrder) {


    }
}