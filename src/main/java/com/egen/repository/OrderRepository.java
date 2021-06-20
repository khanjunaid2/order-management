package com.egen.repository;

import com.egen.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, String> {

    //@Query("SELECT ord FROM Order ord JOIN fetch ord.paymentDetails JOIN FETCH ord.items")
    @Query("SELECT ord FROM Order ord ")
    List<Order> getAllOrders();

    @Query("Select ord from Order ord where ord.id = ?1")
    Order getOrderById(String id);

//    @Query("SELECT ord FROM Order ord  " +
//            "JOIN fetch ord.paymentDetails JOIN FETCH ord.items " +
//            "WHERE ord.createdAt > ?1 AND ord.createdAt < ?2")
    @Query("SELECT ord FROM Order ord  " +
            "WHERE ord.createdAt BETWEEN : startTime AND : endTime")
    List<Order> getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime);

    @Override
    Order save(Order order);
}
