package com.egen.ordermanagment.repository;

import com.egen.ordermanagment.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, String> {
    List<Orders> findAllByOrderCreatedBetween(Timestamp startTime, Timestamp endTime);

    @Query(nativeQuery = true, value = "SELECT * FROM Orders ord,Address addr WHERE ord.shipping_address_id = addr.address_id and addr.order_zip =:paramzip" +
            " order by ord.order_total Desc limit 10")
    List<Orders> findTop10OrdersWithHighestDollarAmountInZip(@Param("paramzip") String paramzip);
}
