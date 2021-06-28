package com.egen.ordermanagement.service.order;

import com.egen.ordermanagement.dto.OrderDTO;
import com.egen.ordermanagement.dto.OrderStatusDTO;
import com.egen.ordermanagement.exception.OrderRequestProcessException;
import com.egen.ordermanagement.exception.OrderServiceException;
import com.egen.ordermanagement.mapper.CircularMappingResolver;
import com.egen.ordermanagement.mapper.OrderMapper;
import com.egen.ordermanagement.model.entity.CustomerOrder;
import com.egen.ordermanagement.model.enums.OrderStatus;
import com.egen.ordermanagement.repository.ItemRepository;
import com.egen.ordermanagement.repository.OrderRepository;
import com.egen.ordermanagement.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private ItemRepository itemRepository;
    private PaymentRepository paymentRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ItemRepository itemRepository, PaymentRepository paymentRepository) {

       this.orderRepository = orderRepository;
       this.itemRepository = itemRepository;
       this.paymentRepository = paymentRepository;
    }

    @Override
    public List<OrderDTO> getAllOrders() {

        try {
            List<OrderDTO> orderDTOList = convertToDTOList(orderRepository.findAll());
            System.out.println("dto:"+orderDTOList.get(0).toString());
             return orderDTOList;

           } catch (Exception e) {

            e.printStackTrace();
            log.error("Error while fetching all orders");
            throw new OrderServiceException("Failed to fetch all orders", e);
        }
    }

    @Override
    public List<OrderDTO> getAllOrdersWithPaginationAndSorted(int pageNumber, int pageSize, String sortBy) {

        try {
            Page<CustomerOrder> pagedOrders = orderRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by("creationDate")));

            if (pagedOrders.hasContent())
                return convertToDTOList(pagedOrders.getContent());
            else
                throw new OrderRequestProcessException("No orders were placed on the given date");

        } catch (Exception e) {

            log.error("Error while retrieving all orders with pagination and sorting");
            throw new OrderServiceException("Failed to retrieve all orders with pagination and sorting", e);
        }
    }

    @Override
    public OrderDTO getOrderById(String id) {

        try {
            Optional<CustomerOrder> order = orderRepository.findById(id);

            if(order.isEmpty())
                throw new OrderRequestProcessException("Order" + id + " you're looking for couldn't be found");

            return OrderMapper.MAPPER.orderToOrderDTO(order.get(), new CircularMappingResolver());

        } catch (Exception e) {
           log.error("Error while fetching specific order");
            throw new OrderServiceException("Failed to fetch specific order", e);
        }
    }

    @Override
    public List<OrderDTO> getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime) {

        try {
            return convertToDTOList(orderRepository.findAllOrdersWithInInterval(startTime, endTime));

        } catch (Exception e) {
            log.error("Error while fetching all orders within the given interval");
            throw new OrderServiceException("Failed to fetch all orders within the given interval", e);
        }
    }

    @Override
    public List<OrderDTO> top10OrdersWithHighestDollarAmountInZip(String zip) {

        try {
            List<CustomerOrder> allOrders = orderRepository.findAll();

            if(allOrders.size() == 0)
                throw new OrderRequestProcessException("No orders have been placed yet at a given location.");

            return convertToDTOList(allOrders.stream()
                                              .filter(order -> order.getShipping().getZip().equals(zip))
                                              .sorted((o1, o2) -> Double.compare(o2.getTotal(), o1.getTotal()))
                                              .limit(10)
                                              .collect(Collectors.toList()));
        } catch (Exception e) {
            log.error("Error while fetching top 10 orders in a given location");
            throw new OrderServiceException("Failed to fetch top 10 orders", e);
        }
    }

    @Override
    public OrderDTO placeOrder(OrderDTO orderDTO) {

        try {
            CustomerOrder placedOrder = orderRepository.save(OrderMapper.MAPPER
                                                                        .orderDTOToOrder(orderDTO, new CircularMappingResolver()));

            // takes care of order to items one to many bi directional mapping
            placedOrder.getItems().stream().map(item -> item.setOrders(placedOrder))
                                           .forEach(updateItem -> itemRepository.save(updateItem));

            // takes care of order to payments one to many bi directional mapping
            placedOrder.getPayments().stream().map(payment -> payment.setOrders(placedOrder))
                                              .forEach(item -> paymentRepository.save(item));

            return OrderMapper.MAPPER.orderToOrderDTO(placedOrder, new CircularMappingResolver());

        } catch (Exception e) {
            log.error("Error while order creation");
            e.printStackTrace();
            throw new OrderServiceException("Failed order creation", e);
        }
    }

    @Override
    public OrderDTO cancelOrder(String orderId) {

        try {
             Optional<CustomerOrder> order = orderRepository.findById(orderId);

             if(order.isEmpty())
                 throw new OrderRequestProcessException("Order doesn't exist");

             else if(order.get().getStatus().equals(OrderStatus.SHIPPED))
                 throw new OrderRequestProcessException("shipped orders can't be cancelled");

             else {
                 order.get().setStatus(OrderStatus.CANCELLED);
                 orderRepository.save(order.get());
                 return OrderMapper.MAPPER.orderToOrderDTO(order.get(), new CircularMappingResolver());
             }

        } catch (Exception e) {
            log.error("Error while order creation");
            throw new OrderServiceException("Failed order cancellation", e);
        }
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) {

        try {
            CustomerOrder order = OrderMapper.MAPPER.orderDTOToOrder(orderDTO, new CircularMappingResolver());
            Optional<CustomerOrder> existingOrder = orderRepository.findById(order.getOrderId());

            if(existingOrder.isEmpty())
                throw new OrderServiceException("Order doesn't exist");

            else if (existingOrder.get().getStatus().equals(OrderStatus.SHIPPED))
                throw new OrderServiceException("Shipped orders can't be modified");

            else
                return OrderMapper.MAPPER.orderToOrderDTO(orderRepository.save(order), new CircularMappingResolver());

        } catch (Exception e) {
            log.error("Error while order modification");
            throw new OrderServiceException("Failed order modification", e);
        }
    }

    /**
     * Order status update
     */
    @Override
    public void updateOrderStatus(OrderStatusDTO orderStatusDTO) {

         Optional<CustomerOrder> order = orderRepository.findById(orderStatusDTO.getOrderId());

         if(order.isPresent())
             orderRepository.save(order.get().setStatus(OrderStatus.valueOf(orderStatusDTO.getOrderStatus())));
         else {
             log.error("Error while order status update");
             throw new OrderServiceException("order not found");
         }
    }

    private List<OrderDTO> convertToDTOList(List<CustomerOrder> orders) {

        return orders.stream()
                     .map(order -> OrderMapper.MAPPER.orderToOrderDTO(order, new CircularMappingResolver()))
                     .collect(Collectors.toList());
    }
}