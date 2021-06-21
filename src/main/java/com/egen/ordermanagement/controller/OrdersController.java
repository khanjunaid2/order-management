package com.egen.ordermanagement.controller;

import com.egen.ordermanagement.dto.CustomerDto;
import com.egen.ordermanagement.dto.ItemDto;
import com.egen.ordermanagement.dto.OrderDto;
import com.egen.ordermanagement.model.Customer;
import com.egen.ordermanagement.model.Item;
import com.egen.ordermanagement.model.Orders;
import com.egen.ordermanagement.service.CustomerService;
import com.egen.ordermanagement.service.ItemService;
import com.egen.ordermanagement.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrdersController {

    @Autowired
    CustomerService customerService;
    @Autowired
    OrdersService ordersService;
    @Autowired
    ItemService itemService;

    /**
     * Fetches all the orders present in the table
     * @return list of orders
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Orders>> getAllOrders(){

        return ResponseEntity.ok(ordersService.findAll());
    }

    /**
     * Fetches order details of the given id
     * @param id accepts Order id as input
     * @return Order object
     */
    @RequestMapping(method = RequestMethod.GET,value = "/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Orders> getOrderById(@PathVariable("id")Long id){
        return ResponseEntity.ok(ordersService.findOne(id));
    }

    /**
     * Fetches all the records of orders places between the given interval
     * @param startTime - Time of the order placed
     * @param endTime - Time of the order placed
     * @return List of orders
     */
    @RequestMapping(method = RequestMethod.GET,value = "/interval",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Orders>> getAllOrdersWithInInterval(@RequestParam(name = "startTime") Timestamp startTime,
                                                   @RequestParam(name = "endTime") Timestamp endTime){
        return ResponseEntity.ok(ordersService.findWithinInterval(startTime,endTime));
    }

    /**
     * Fetches the top 10 highest amount in the given area
     * @param zip - Zipcode of the address
     * @return List of orders
     */
    @RequestMapping(method = RequestMethod.GET,value = "zipcode/{zip}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Orders>> top10OrdersWithHighestDollarAmountInZip(@PathVariable("zip") String zip){
        return ResponseEntity.ok(ordersService.findTop10OrdersWithHighestDollarAmountInZip(zip));
    }

    @RequestMapping(method = RequestMethod.POST,value = "/placeorder",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Orders> placeOrder(@RequestBody OrderDto orderDto){

       return new ResponseEntity<>(ordersService.createOrder(orderDto), HttpStatus.CREATED);
    }

    /**
     * This method will cancel the selected order
     * @param id - Order id
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT,value = "/cancel/{id}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Orders>  cancelOrder(@PathVariable("id")Long id){

        return ResponseEntity.ok(ordersService.cancelOrder(id));
    }

    /**
     * Updates the selected order by changing the values desired
     * Currently this method is used to change the order status to delivered
     * @param id - order id
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT,value = "/updateorder/{id}",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Orders> updateOrder(@RequestBody OrderDto orderDto,@PathVariable("id")Long id){
        return ResponseEntity.ok(ordersService.updateOrder(orderDto,id));
    }




}
