package com.egen.ordermanagement.controller;

import com.egen.ordermanagement.dto.OrderDTO;
import com.egen.ordermanagement.dto.OrderStatusDTO;
import com.egen.ordermanagement.service.kafka.ProducerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This controller class provides end points to the clients such as POS Terminals, fulfilment systems, warehouse picking systems etc
 * to work with bulk orders.
 */
@RestController
@RequestMapping(value = "/batch/orders")
@Api(tags = {"batchOrders"})
@SwaggerDefinition(tags = {@Tag(name = "batchOrders", description = "batch Orders Service Endpoints")})
public class BatchOrderController {

    private final ProducerService producerService;

    public BatchOrderController(ProducerService producerService) {
        this.producerService = producerService;
    }

    /**
     * end point to create orders in batch
     */
    @PostMapping(value = "/create")
    @ApiOperation(value = "create an order",
                  notes = "Returns success status.")
    public ResponseEntity<Void> batchOrder(@RequestBody List<OrderDTO> orders) {

        producerService.sendBatchOrder(orders);
        return new ResponseEntity<>(null, HttpStatus.CREATED); //as clients are not UI clients I am not sending http status codes
    }

    /**
     * end point to update order status in batch
     */
    @PostMapping(value = "/update/status")
    @ApiOperation(value = "update order status",
                  notes = "Returns success status.")
    public ResponseEntity<Void> updateOrderStatusInBatch(@RequestBody List<OrderStatusDTO> orderStatusDTOS) {

        producerService.updateOrderStatus(orderStatusDTOS);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
