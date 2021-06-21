package com.egen.repository;

import com.egen.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order,Long> {

    @Query("UPDATE Order ord SET ord.status=:stat where ord.id=:id")
    public Order cancel(@Param("id") long id);
    @Query("SELECT ord FROM Order ord JOIN Address add ON ord.shippingAddress.id=add.id WHERE add.zip=:paramZip ORDER BY ord.total DESC")
    public List<Order> findTop10OrdersWithHighestDollarAmountInZip(@Param("paramZip") String paramZip);
    @Query("SELECT ord FROM Order ord  WHERE ord.createdDate BETWEEN :ordStartTime AND :ordEndTime")
    public List<Order> getAllOrdersWithInInterval(@Param("ordStartTime") Timestamp ordStartTime, @Param("ordEndTime") Timestamp ordEndTime);
}
