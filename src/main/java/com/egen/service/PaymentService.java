package com.egen.service;

import com.egen.exception.BadRequestException;
import com.egen.exception.ResourceNotFoundException;
import com.egen.model.Payment;
import com.egen.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepositoryImpl;

    public List<Payment> findAll(){
        List<Payment> list = (List<Payment>) paymentRepositoryImpl.findAll();
        if(list.size() == 0) {
            throw new ResourceNotFoundException("Payment table is empty");
        }
        return list;
    }

    public Payment getById(long id) {
        Optional<Payment> existing = paymentRepositoryImpl.findById(id);
        if(!existing.isPresent()) {
            throw new BadRequestException("Payment with id " + id + " does not exists");
        }
        return existing.get();
    }

    @Transactional
    public Payment create(Payment payment){
        Optional<Payment> existing = paymentRepositoryImpl.findById(payment.getId());
        if(!existing.isPresent()) {
            throw new BadRequestException("Payment with id " + payment.getId() + "already exists");
        }
        return paymentRepositoryImpl.save(payment);
    }

    @Transactional
    public Payment update(Payment payment){
        Optional<Payment> existing = paymentRepositoryImpl.findById(payment.getId());
        if(!existing.isPresent()) {
            throw new BadRequestException("Payment with id " + payment.getId() + " is up to date.");
        }
        return paymentRepositoryImpl.save(payment);
    }

    @Transactional
    public void delete(long id){
        Optional<Payment> existing = paymentRepositoryImpl.findById(id);
        if(!existing.isPresent()) {
            throw new BadRequestException("Payment with id " + id + "does not exists");
        }
        paymentRepositoryImpl.deleteById(id);
    }
}
