package com.example.ordermanagement.DTO;

import com.example.ordermanagement.enums.PaymentMethod;
import com.example.ordermanagement.models.Address;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class PaymentDto {


//    @Id
//    private String paymentId;

    private double amount;
    private Timestamp order_payment_date;

    private PaymentMethod paymentMethod;
    private Address billingAddress;
}
