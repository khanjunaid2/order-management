package com.egen.repo;

import com.egen.model.Order;
import com.egen.model.OrderEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public interface OrderRepoImplementation extends CrudRepository<Order, String> {
}

  /*  @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Order> findall() {
        Query q = entityManager.createQuery("SELECT od FROM Order od");
        return q.getResultList();
    }

    @Override
    public Order findOne(String id) {
        Query q = entityManager.createQuery("SELECT od FROM Order od WHERE od.id =: order_id");
        q.setParameter("order_id", id);
        List<Order> res = q.getResultList();
        if (res.isEmpty()) {
            return null;
        }
        return res.get(0);
    }

    @Override
    public List<Order> findWithinInterval(Timestamp start, Timestamp end) {
        Query q = entityManager.createQuery("SELECT od FROM Order od WHERE od.dateOrdered BETWEEN :startDate AND :endDate");
        q.setParameter("startDate", start);
        q.setParameter("endDate", end);
        List<Order> res = q.getResultList();
        if(res.isEmpty())
        {
            return null;
        }
        return res;
    }

    @Override
    public List<Order> findTop10OrdersWithHighestDollarAmountInZip(String zip) {
        Query q = entityManager.createQuery("SELECT od FROM Order od, Address ad WHERE od.shippingAddress.id = ad.id AND ad.zipcode = :zipCode ORDER BY od.subTotal desc");
        q.setParameter("zipCode", zip);
        List<Order> res = q.getResultList();
        if(res.isEmpty())
        {
            return null;
        }
        if(res.size() < 10)
            return res;
        List<Order> final_res = new ArrayList<>();
        for(int i =0; i < 10; i++)
        {
            final_res.add(res.get(i));
        }
        return final_res;
    }

    @Override
    public Order creteOrder(Order order) {
        entityManager.persist(order);
        return order;
    }

    @Override
    public Order cancelOrder(String id, Order order) {
        Order od = findOne(id);
        od.setOrderStatus(order.getOrderStatus());
        entityManager.persist(od);
        return od;
    }

    @Override
    public Order updateOrder(String id) {
        Order od = findOne(id);
        od.setOrderStatus(OrderEnum.DELIVERED);
        return od;
    }
*/