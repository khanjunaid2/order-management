package com.example.ordermanagement.DTO;

import com.example.ordermanagement.enums.DeliveryMethod;
import com.example.ordermanagement.enums.OrderStatus;
import com.example.ordermanagement.models.Address;
import com.example.ordermanagement.models.Customer;
import com.example.ordermanagement.models.Item;
import com.example.ordermanagement.models.Payment;
import lombok.Data;

import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
public class OrdersDto {

    @Id
    private  String orderId;
    private OrderStatus orderStatus;

    private double subTotal;

    private double tax;

    private double totalAmount;

    private Address shippingAddress;

    private DeliveryMethod deliveryMethod;

    private List<Item> items;

    private List<Payment> paymentDetails;

    private Timestamp createdAt;
    private Timestamp orderModified;

    private Customer customer;

    public OrdersDto() {
        this.orderId = UUID.randomUUID().toString();
    }
}
