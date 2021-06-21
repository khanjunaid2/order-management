package com.egen.service;

import com.egen.model.Address;
import com.egen.model.Order;
import com.egen.model.Payments;

import java.util.List;

public interface PaymentService {
    void createPayment(List<Payments> payment, Address adr, Order od);
}
