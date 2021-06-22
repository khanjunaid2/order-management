package com.egen.repository;

import com.egen.model.Order;
import com.egen.model.enums.OrderStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;


@Repository
@Transactional
public class OrderRepositoryImpl implements OrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Order> findAllOrders() {
        Query query = entityManager.createQuery("SELECT ord FROM Order ord JOIN fetch ord.orderPayments JOIN FETCH ord.orderItems");
        List<Order> orders = (List<Order>) query.getResultList();
        return orders;
    }

    @Override
    public Order findOrderById(String orderId) {
        Query query = entityManager.createNativeQuery("Select * from Order where orderId = :orderId");
        query.setParameter("orderId", orderId);
        List<Order> orders = query.getResultList();
        return orders.get(0);
    }

    @Override
    public List<Order> findAllOrdersWithInInterval(ZonedDateTime startTime, ZonedDateTime endTime) {

        Query query = entityManager.createQuery("SELECT o FROM Order o JOIN fetch o.orderPayments JOIN fetch o.orderItems WHERE o.createdAt > :startTime AND o.createdAt < :endTime")
                .setParameter("startTime", startTime)
                .setParameter("endTime", endTime);
        return (List<Order>) query.getResultList();
    }

    public List<Order> OrdersWithHighestDollarAmountInZip(String zip) {

        // Yet to be check
        String sqlQuery = "SELECT S.order_id\n" +
                "FROM (SELECT o.order_id, max(o.order_total)  as order_total" +
                "  FROM orders o, shipping s" +
                "  WHERE o.order_id = s.order_order_id and s.order_shipping_zip = \"10\"" +
                "  GROUP BY o.order_id" +
                "  ORDER BY o.order_total DESC" +
                "  limit 10" +
                ") highestAmount" +
                " INNER JOIN orders S" +
                "  ON highestAmount.order_id = S.order_id" +
                "  AND highestAmount.order_total = S.order_total";
        return entityManager.createNativeQuery(sqlQuery).getResultList();
    }

    @Override
    public Order saveOrder(Order order) {
        entityManager.persist(order);
        return order;
    }

    @Override
    public Order cancelOrder(Order order) {
        Order savedOrder = findOrderById(order.getOrderId());
        savedOrder.setOrderStatus(OrderStatus.CANCELLED);

        return entityManager.merge(savedOrder);
    }

    @Override
    public Order updateOrder(Order order) {
        entityManager.merge(order);
        return order;
    }
}
