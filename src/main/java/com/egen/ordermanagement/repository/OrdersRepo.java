package com.egen.ordermanagement.repository;

import com.egen.ordermanagement.model.Orders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface OrdersRepo extends CrudRepository<Orders,Long> {

   // @Query("From Orders ord WHERE ord.dateOrdered BETWEEN :startTime AND :endTime")
    List<Orders> findAllByDateOrderedBetween(Timestamp startTime, Timestamp endTime);

    @Query(nativeQuery = true, value = "SELECT * FROM Orders ord,Address adr WHERE ord.shipping_address_id=adr.id " +
                  "AND adr.zipcode =:zipcode ORDER BY ord.total desc limit 10")
    List<Orders> findMaxTotalAmountInParticularArea(String zipcode);

}
