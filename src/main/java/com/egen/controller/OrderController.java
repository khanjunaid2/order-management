package com.egen.controller;

import com.egen.dto.OrderDTO;
import com.egen.model.Order;
import com.egen.repository.OrderRepository;
import com.egen.response.Response;
import com.egen.response.ResponseMetadata;
import com.egen.response.StatusMessage;
import com.egen.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {


    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("order")
    public ResponseEntity<List<OrderDTO>> getAllOrders(){
       List<OrderDTO> orderDTOList = new ArrayList<>();
        List<Order> orderList= orderRepository.getAllOrders();
       for (Order order: orderList){
            OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(order,orderDTO);
        orderDTOList.add(orderDTO);

        }
 //      BeanUtils.copyProperties(orderList,orderDTOList);
////        System.out.println(ResponseEntity.ok(orderDTOList));
        return ResponseEntity.ok(orderDTOList);
    }

    @GetMapping(value = "order/{id}",consumes = "application/json",produces = "application/json")
    public Response<Order> getOrderById(@RequestBody String id){
            return Response.<Order>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data((orderService.getOrderById(id)))
                .build();
    }

    @GetMapping("ordersInInterval/{startTime}/{endTime}")
    public ResponseEntity<List<Order>> getAllOrdersWithInInterval(@PathVariable Timestamp startTime, @PathVariable Timestamp endTime){
        return ResponseEntity.ok(orderService.getAllOrdersWithInInterval(startTime,endTime));
    }

    @GetMapping("topOrdersInZip/{zip}")
    public ResponseEntity<List<Order>> top10OrdersWithHighestDollarAmountInZip(@PathVariable String zip){
        return ResponseEntity.ok(orderService.top10OrdersWithHighestDollarAmountInZip(zip));
    }

    @PostMapping("/save")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order){
        return new ResponseEntity<>(orderService.saveOrder(order), HttpStatus.CREATED);
    }

    @PutMapping("/cancel")
    public ResponseEntity<Order> cancelOrder(@RequestBody Order order){
        return ResponseEntity.ok(orderService.cancelOrder(order));
    }

    @PutMapping("/update")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order){
        return ResponseEntity.ok(orderService.updateOrder(order));
    }

    @PostMapping("random/{num}")
    public ResponseEntity<String> addRandomOrder(@PathVariable int num){
        return new ResponseEntity<>(orderService.createRandomOrders(num),HttpStatus.CREATED);
    }
}
