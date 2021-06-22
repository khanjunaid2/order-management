package com.egen.ordermanagement.service;

import com.egen.ordermanagement.exceptions.OrderServiceException;
import com.egen.ordermanagement.exceptions.PaymentServiceException;
import com.egen.ordermanagement.model.Address;
import com.egen.ordermanagement.model.Orders;
import com.egen.ordermanagement.model.Payment;
import com.egen.ordermanagement.repository.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    PaymentRepo paymentRepo;

    @Transactional
    public List<Payment> createPayment(List<Payment> payment, Address billingAddr, Orders orderId) {
        try {
            Date date = new Date();
            Timestamp payment_date = new Timestamp(date.getTime());
            payment.forEach(paymentMade -> {
                Payment new_payment = new Payment(paymentMade.getAmount(), payment_date, paymentMade.getPaymentMode(),
                        billingAddr, orderId);
                paymentRepo.save(new_payment);
            });
            return payment;
        }catch (Exception ex){
            throw new PaymentServiceException("Failed to process the transaction",ex);
        }
    }
}
