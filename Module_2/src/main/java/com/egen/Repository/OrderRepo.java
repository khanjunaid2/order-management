package com.egen.Repository;

import com.egen.DTO.OrderDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface OrderRepo extends CrudRepository<OrderDTO, Long> {

    @Query(nativeQuery = true, value = "SELECT ord FROM Order ord JOIN fetch ord.paymentDetails JOIN FETCH ord.items")
    List<OrderDTO> getAllOrders();

    @Query ("SELECT ord FROM Order ord" + "JOIN fetch ord.paymentDetails JOIN FETCH ord.items" +
            "WHERE ord.orderId = :orderId ")
    OrderDTO getOrderById(@Param("orderId") String orderId);

    @Query("SELECT ord FROM Order ord" + "JOIN fetch ord.paymentDetails JOIN FETCH ord.items" +
            "WHERE ord.createdAt > :startTime AND ord.createdAt < :endTime")
    List<OrderDTO> getOrdersWithTimeInterval(@Param("startTime") ZonedDateTime startTime, @Param("endTime") ZonedDateTime endTime);

    @Query("SELECT ord FROM Order ord, Address adr WHERE ord.shipping.shippingId = adr.addressId AND adr.zipCode = :zip_code ORDER BY ord.order_total desc")
    List<OrderDTO> top10OrdersWithHighestDollarAmountInZip(@Param("zip") String zip);

//    public Order addOrder(Order orders) {
//        em.persist(orders);
//        return orders;
//    }
//    public OrderStatus cancelOrder(String id) {
//        Order cancelOrder = getOrderById(id);
//        cancelOrder.setOrderStatus(OrderStatus.CANCELLED);
//        em.merge(cancelOrder);
//        return OrderStatus.CANCELLED;
//    }
//
//    @Override
//    public OrderStatus updateOrder(String id, Order order) {
//        Order orders = getOrderById(id);
//        orders.setOrderStatus(OrderStatus.MODIFIED);
//        em.merge(order);
//        return OrderStatus.MODIFIED;
//    }
}
