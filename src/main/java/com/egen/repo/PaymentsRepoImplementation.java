package com.egen.repo;

import com.egen.model.Address;
import com.egen.model.Order;
import com.egen.model.Payments;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public class PaymentsRepoImplementation implements PaymentsRepo{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createPayment(List<Payments> paymentsList, Address address, Order order) {
        Date date = new Date();
        Timestamp payment_date = new Timestamp(date.getTime());
        for(int i=0; i< paymentsList.size(); i++)
        {
            Payments p = new Payments(paymentsList.get(i).getAmount(), paymentsList.get(i).getOrderPaymentMethod(),payment_date.toString(), paymentsList.get(i).getOrderPaymentConfirmationNumber(), address, order);
            entityManager.persist(p);
        }
    }
}
