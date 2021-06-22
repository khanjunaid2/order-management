package com.egen.ordermanagement.repository;

import com.egen.ordermanagement.model.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface OrdersRepo extends JpaRepository<Orders,Long> {


    List<Orders> findAllByDateOrderedBetween(Timestamp startTime, Timestamp endTime);

    @Query(nativeQuery = true, value = "SELECT * FROM Orders ord,Address adr WHERE ord.shipping_address_id=adr.id " +
                  "AND adr.zipcode =:zipcode ORDER BY ord.total desc limit 10")
    List<Orders> findAllByShippingAddressZipcodeAndSubTotal(String zipcode);

    Page<Orders> findAll(Pageable pageable);
}
