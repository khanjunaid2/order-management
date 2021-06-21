package com.egen.service;

import com.egen.model.Address;
import com.egen.model.Order;
import com.egen.model.Payments;
import com.egen.repo.PaymentsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PaymentServiceImplementation implements PaymentService{

    @Autowired
    PaymentsRepo paymentsRepo;

    @Transactional
    public void createPayment(List<Payments> payment, Address adr, Order od) {
        paymentsRepo.createPayment(payment,adr, od);
    }
}
