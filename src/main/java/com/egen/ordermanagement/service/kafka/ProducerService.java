package com.egen.ordermanagement.service.kafka;

import com.egen.ordermanagement.dto.OrderDTO;
import com.egen.ordermanagement.dto.OrderStatusDTO;

import java.util.List;

public interface ProducerService {

    boolean sendBatchOrder(List<OrderDTO> orderDTO);
    boolean updateOrderStatus(List<OrderStatusDTO> orderStatusDTO);
}
