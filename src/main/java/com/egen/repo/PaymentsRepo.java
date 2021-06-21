package com.egen.repo;

import com.egen.model.Address;
import com.egen.model.Order;
import com.egen.model.Payments;

import java.util.List;

public interface PaymentsRepo {
    void createPayment(List<Payments> paymentsList, Address address, Order order);
}
