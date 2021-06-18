package com.egen.ordermanagement.service;

import com.egen.ordermanagement.model.Address;
import com.egen.ordermanagement.model.Orders;
import com.egen.ordermanagement.model.Payment;
import com.egen.ordermanagement.repository.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    PaymentRepo paymentRepo;

    public void createPayment(List<Payment> payment, Address billingAddr, Orders orderId) {
        System.out.println("updating payment");
        Date date = new Date();
        Timestamp payment_date = new Timestamp(date.getTime());
        for(int i=0;i<payment.size();i++){
            Payment new_payment = new Payment(payment.get(i).getAmount(),payment_date,payment.get(i).getPaymentMode(),
                    billingAddr,orderId);
            System.out.println(new_payment.toString());
            paymentRepo.save(new_payment);
        }
    }
}
