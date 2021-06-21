package me.simran.repository;

import me.simran.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, String> {

    @Query("SELECT order FROM Order order where order.dateCreated>:startTime and order.dateCreated<:endTime")
    List<Order> getAllOrdersWithinInterval(@Param("startTime") java.sql.Date startTime, @Param("endTime") java.sql.Date endTime);

    @Query("SELECT order FROM Order order where order.customer.billingZip=:zip order by order.payment.total DESC")
    List<Order> getTop10OrdersWithHighestDollarAmountInZip(@Param("zip") String zip);

}
