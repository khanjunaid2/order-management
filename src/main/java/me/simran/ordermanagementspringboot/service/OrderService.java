package me.simran.ordermanagementspringboot.service;

import me.simran.ordermanagementspringboot.dto.OrderDTO;
import me.simran.ordermanagementspringboot.entity.Order;
import me.simran.ordermanagementspringboot.entity.OrderPage;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {
    /** List all orders */
    Page<Order> getAllOrders(OrderPage orderPage);

    /** Creating a single order */
    Boolean createOrder(OrderDTO orderDto);

    /** Creating a List of orders at once */
    Boolean createOrders(List<OrderDTO> orderDto);

    /** Getting order by ID */
    Order getOrderById(Long id);

    /** Updating an order*/
    Boolean update(OrderDTO orderDto, Long id);

    /** Deleting an order by id */
    Boolean delete(Long id);

}
