package com.egen.repo;

import com.egen.model.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomerRepoImplementation implements CustomerRepo{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean findCust(String id) {
        Query q = entityManager.createQuery("SELECT c  FROM Customer c WHERE c.id = :c_id");
        q.setParameter("c_id", id);
        List<Customer> res = q.getResultList();
        if(res.isEmpty())
            return false;
        else
            return true;
    }
}
