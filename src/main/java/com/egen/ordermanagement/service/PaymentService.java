package com.egen.ordermanagement.service;

import com.egen.ordermanagement.model.Address;
import com.egen.ordermanagement.model.Orders;
import com.egen.ordermanagement.model.Payment;

import java.util.List;

public interface PaymentService {
    void createPayment(List<Payment> payment, Address addr, Orders ord);
}
