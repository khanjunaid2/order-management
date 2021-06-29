/*
package com.egen.repo.impl;

import com.egen.model.GroceryOrder;
import com.egen.model.OrderStatus;
import com.egen.repo.IOrderManagementRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Repository
@Transactional
public class OrderManagementRepo implements IOrderManagementRepo {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<GroceryOrder> getAllOrders() {
        Query query = em.createQuery("SELECT ord FROM GroceryOrder ord  JOIN fetch ord.paymentDetails JOIN FETCH ord.items");
        List<GroceryOrder> groceryOrders= (List<GroceryOrder>) query.getResultList();
        return groceryOrders;
    }

    @Override
    public GroceryOrder getOrderById(String orderId) {
        Query nativeQuery = em.createNativeQuery("Select * from GroceryOrder where orderId = :orderId");
        nativeQuery.setParameter("orderId",orderId);
        List<GroceryOrder> orders = nativeQuery.getResultList();

        //----------
        Query query = em.createQuery("SELECT ord FROM GroceryOrder ord  " +
                "JOIN fetch ord.paymentDetails JOIN FETCH ord.items " +
                "WHERE ord.orderId = :orderId ")
                .setParameter("orderId",orderId);
        List<GroceryOrder> groceryOrders = query.getResultList();

        if(groceryOrders.size() == 0 )
            return null;
        return groceryOrders.get(0);
    }

    @Override
    public List<GroceryOrder> getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime) {
        Query query = em.createQuery("SELECT ord FROM GroceryOrder ord  " +
                "JOIN fetch ord.paymentDetails JOIN FETCH ord.items " +
                "WHERE ord.createdAt > :startTime AND ord.createdAt < :endTime")
                .setParameter("startTime", startTime).setParameter("endTime", endTime);
        return (List<GroceryOrder>) query.getResultList();
    }

    @Override
    public GroceryOrder saveOrder(GroceryOrder groceryOrder) {
         em.persist(groceryOrder);
         return groceryOrder;
    }

    @Override
    public GroceryOrder cancelOrder(GroceryOrder groceryOrder) {
        GroceryOrder savedGroceryOrder = getOrderById(groceryOrder.getOrderId());
        savedGroceryOrder.setOrderStatus(OrderStatus.CANCEL);
        em.merge(savedGroceryOrder);
        return savedGroceryOrder;
    }

    @Override
    public GroceryOrder updateOrder(GroceryOrder groceryOrder) {
        em.merge(groceryOrder);
        return groceryOrder;
    }
}
*/
