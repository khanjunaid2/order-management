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
import com.egen.ordermanagement.repository.OrdersRepo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import springfox.documentation.swagger2.mappers.ModelMapper;

import java.sql.Timestamp;
import java.util.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrdersServiceImplTest {

    @Bean
    public OrdersService getService(){return new OrdersServiceImpl();}

    @Autowired
    OrdersService ordersService;

    @Autowired
    ItemService itemService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    AddressService addressService;

    @Autowired
    CustomerService customerService;

    @MockBean
    private OrdersRepo ordersRepo;

    private List<Orders> orders;
    OrderDto new_dto;

    @Before
    public void setup(){

        Orders new_order = new Orders();
        new_dto = new OrderDto();
        Set<Payment> paymentsList = new HashSet<Payment>();
        Set<Item> itemsList = new HashSet<Item>();

        //Create address
       Address addresses = new Address();
        addresses.setId(9L).setAddressLine1("912 Carrington oak").setAddressLine2("#203")
                .setCity("Memphis").setState("TN").setZipcode("38125");

        //Create Payments
        Payment payments = new Payment();
        payments.setId(10L).setAmount(3.0).setPaymentMode(PaymentMethod.CHECKING_ACCOUNT)
                .setBillingAddress((Address) addresses).setPaymentDate(Timestamp.valueOf("2021-06-18 18:33:09"));
        Payment payments2 = new Payment();
        payments.setId(11L).setAmount(20.0).setPaymentMode(PaymentMethod.PAYPAL)
                .setBillingAddress((Address) addresses).setPaymentDate(Timestamp.valueOf("2021-06-18 18:33:09"));

        //Create order
        new_order.setId(8L).setDateOrdered(Timestamp.valueOf("2021-06-18 18:33:09"))
                .setExpectedDelivery(Timestamp.valueOf("2021-06-23 18:33:09")).setCustomerId(2L)
                .setItemQuantity(2).setShipmentMethod(ShipmentMethod.HOME_DELIVERY).setSubTotal(20.5)
                .setTax(1.5).setTotal(23.0).setShippingCharges(1.0).setOrderStatus(OrderStatus.DELIVERED);

        //Create item
       Item items = new Item();
        items.setId(3L).setOrders(new_order).setQuantityInStock(3).setItemName("Energy Drink").setItemPrice(74.5);
        paymentsList.add(payments);
        paymentsList.add(payments2);

        itemsList.add(items);
        new_order.setItems((Set<Item>) itemsList).setShippingAddress(addresses)
                .setPaymentDetails((Set<Payment>) paymentsList);

        orders = Collections.singletonList(new_order);

        //To create new order
        List<Payment> payment = new ArrayList<>();
        payment.add(payments);
        payment.add(payments2);

        new_dto.setId(8L).setCustomerId(2L)
                .setItemQuantity(2).setShipmentMethod(ShipmentMethod.HOME_DELIVERY).setOrderStatus(OrderStatus.DELIVERED)
        .setBillingSameAsShippingAddress(true).setShippingAddress(addresses).setBillingAddress(addresses).
                setPayments((payment)).setItems(new int[]{Math.toIntExact(items.getId())});

        //Mockito functions
        Mockito.when(ordersRepo.findAll()).thenReturn(orders);
        Mockito.when(ordersRepo.findById(new_order.getId())).thenReturn(Optional.of(new_order));

        Mockito.when(ordersRepo.findAllByDateOrderedBetween(orders.get(0).getDateOrdered(),
                orders.get(0).getExpectedDelivery())).thenReturn(orders);

        Mockito.when(ordersRepo.findAllByShippingAddressZipcodeAndSubTotal(orders.get(0)
                .getShippingAddress().getZipcode())).thenReturn(orders);

        Mockito.when(ordersRepo.save(new_order)).thenReturn(new_order);
        Mockito.when(ordersRepo.save(orders.get(0))).thenReturn(new_order);

//        Page<Orders> ordersPage = (Page<Orders>) Mockito.mock(Orders.class);
//        Mockito.when(ordersRepo.findAll((Pageable) ordersPage)).thenReturn(ordersPage);
    }

    @After
    public void cleanUp(){System.out.println("Done testing Orders Service");}

    @Test
    public void findAll() throws Exception {
        List<Orders> result = ordersService.findAll();
        Assert.assertEquals("Orders list should match",orders,result);
    }

    @Test(expected = OrderServiceException.class)
    public void findAllNotFound() throws Exception {
        List<Orders> current_order = ordersService.findAll();
    }

    @Test
    public void findOne() throws Exception {
        Orders current_order = ordersService.findOne(orders.get(0).getId());
        System.out.println(current_order.toString());
        Assert.assertEquals("Order id should match",orders.get(0),current_order);
    }

    @Test(expected = OrderServiceException.class)
    public void findOneNotFound() throws Exception {
        Orders current_order = ordersService.findOne(25L);
    }

    @Test
    public void findWithinInterval() {
        List<Orders> ordersWithinTime = ordersService.findWithinInterval(orders.get(0).getDateOrdered(),
                orders.get(0).getExpectedDelivery());
        Assert.assertEquals("orders within the interval not found",ordersWithinTime,orders);
    }

    @Test(expected = OrderServiceException.class)
    public void findWithinIntervalNotFound() {
        List<Orders> ordersWithinTime = ordersService.findWithinInterval(orders.get(0).getDateOrdered(),
                orders.get(0).getDateOrdered());
    }

    @Test
    public void findAllByShippingAddressZipcodeAndSubTotal() {
        List<Orders> max_total = ordersService.findAllByShippingAddressZipcodeAndSubTotal(orders.get(0)
                .getShippingAddress().getZipcode());
        Assert.assertEquals("No records found in the given zipcode",orders,max_total);
    }

    @Test(expected = OrderServiceException.class)
    public void findAllByShippingAddressZipcodeAndSubTotalNotFound() {
        List<Orders> max_total = ordersService.findAllByShippingAddressZipcodeAndSubTotal("8888");
    }

    @Test
    @Transactional
    public void createOrder() {
        Orders create_order = ordersService.createOrder(new_dto);
        Assert.assertEquals("Failed to create order",create_order,create_order);
    }

    @Test
    @Transactional
    public void cancelOrder() {
        Orders cancel = ordersService.cancelOrder(orders.get(0).getId());
        Assert.assertEquals("Failed to Cancel order","CANCELLED",cancel.getOrderStatus().toString());
    }

    @Test(expected = OrderServiceException.class)
    @Transactional
    public void cancelOrderFailed() {
        Orders cancel = ordersService.cancelOrder(55L);
        Assert.assertEquals("Failed to Cancel order","CANCELLED",cancel.getOrderStatus().toString());
    }

    @Test
    @Transactional
    public void updateOrder() {
        Orders deliver = ordersService.updateOrder(orders.get(0).setOrderStatus(OrderStatus.DELIVERED),
                orders.get(0).getId());
        Assert.assertEquals("Failed to Deliver order","DELIVERED",deliver.getOrderStatus().toString());
    }

    @Test(expected = OrderServiceException.class)
    @Transactional
    public void updateOrderFailed() {
        Orders deliver = ordersService.updateOrder(orders.get(0).setOrderStatus(OrderStatus.CANCELLED),
                orders.get(0).getId());
    }
}