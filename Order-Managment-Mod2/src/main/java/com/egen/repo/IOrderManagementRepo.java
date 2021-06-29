package com.egen.repo;

import com.egen.model.GroceryOrder;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IOrderManagementRepo extends CrudRepository<GroceryOrder, String> {


    @Query("SELECT ord FROM GroceryOrder ord   "
        + "                JOIN fetch ord.paymentDetails JOIN FETCH ord.items  "
        + "                WHERE ord.createdAt > :startTime AND ord.createdAt < :endTime")
    List<GroceryOrder> getAllOrdersWithInInterval(@Param("startTime") Timestamp startTime, @Param("endTime")Timestamp endTime);

}
