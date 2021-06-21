//package com.egen.ordermanagment.repository;
//
//import com.egen.ordermanagment.model.OrderStatus;
//import com.egen.ordermanagment.model.Orders;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.TypedQuery;
//import java.sql.Timestamp;
//import java.util.List;
//
//@Repository
//public class OrderRepositoryImpl implements OrderRepository {
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public List<Orders> getAllOrders() {
//        TypedQuery<Orders> query = entityManager.createNamedQuery("Orders.getAllOrders", Orders.class);
//        return query.getResultList();
//    }
//
//    @Override
//    public Orders getOrderById(String id) {
//        return entityManager.find(Orders.class, id);
//    }
//
//    @Override
//    public List<Orders> getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime) {
//        TypedQuery<Orders> query = entityManager.createNamedQuery("Order.getAllOrdersWithInInterval", Orders.class);
//        return query.getResultList();
//    }
//
//    @Override
//    public List<Orders> top10OrdersWithHighestDollarAmountInZip(String zip) {
//        TypedQuery<Orders> query = entityManager.createNamedQuery("Order.top10OrdersWithHighestDollarAmountInZip", Orders.class);
//        return query.setMaxResults(10).getResultList();
//    }
//
//    @Override
//    public Orders placeOrder(Orders orders) {
//        entityManager.persist(orders);
//        return orders;
//    }
//
//    @Override
//    public void cancelOrder(String id) {
//        Orders cancelOrder = getOrderById(id);
//        cancelOrder.setOrderStatus(OrderStatus.CANCELLED);
//        entityManager.remove(cancelOrder);
//    }
//
//    @Override
//    public Orders updateOrder(String id, Orders order) {
//        Orders updateOrder = getOrderById(id);
//        entityManager.merge(updateOrder);
//        return updateOrder;
//    }
//}
//
