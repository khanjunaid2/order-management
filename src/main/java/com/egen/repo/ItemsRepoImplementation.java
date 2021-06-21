package com.egen.repo;

import com.egen.model.Items;
import com.egen.model.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ItemsRepoImplementation implements ItemsRepo{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Items getItem(String id) {
        Query q = entityManager.createQuery("SELECT im FROM Items im WHERE im.id = :im_id");
        q.setParameter("im_id", id);
        List<Items> res = q.getResultList();
        if(res.isEmpty())
            return null;
        return res.get(0);
    }

    @Override
    public void updateItem(String id, int quantity) {
        Items itm = getItem(id);
        int left = itm.getQuantity_in_stock() - quantity;
        if (left>0)
        {
            itm.setQuantity_in_stock(left);
        }
        entityManager.persist(itm);
    }

    @Override
    public void updateOrderIdItem(String id, Order order) {
        Items itm = getItem(id);
        itm = new Items(id, itm.getItem_name(),itm.getItem_price(), itm.getQuantity_in_stock(), order);
        entityManager.persist(itm);
    }
}
