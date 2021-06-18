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
import org.springframework.http.MediaType;
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
    public List<Orders> getAllOrders(){
        return ordersService.findAll();
    }

    /**
     * Fetches order details of the given id
     * @param id accepts Order id as input
     * @return Order object
     */
    @RequestMapping(method = RequestMethod.GET,value = "/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Orders getOrderById(@PathVariable("id")Long id){
        return ordersService.findOne(id);
    }

    /**
     * Fetches all the records of orders places between the given interval
     * @param startTime - Time of the order placed
     * @param endTime - Time of the order placed
     * @return List of orders
     */
    @RequestMapping(method = RequestMethod.GET,value = "/interval",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Orders> getAllOrdersWithInInterval(@RequestParam(name = "startTime") Timestamp startTime,
                                                   @RequestParam(name = "endTime") Timestamp endTime){
        return ordersService.findWithinInterval(startTime,endTime);
    }

    /**
     * Fetches the top 10 highest amount in the given area
     * @param zip - Zipcode of the address
     * @return List of orders
     */
    @RequestMapping(method = RequestMethod.GET,value = "zipcode/{zip}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Orders> top10OrdersWithHighestDollarAmountInZip(@PathVariable("zip") String zip){
        return ordersService.findTop10OrdersWithHighestDollarAmountInZip(zip);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/placeorder",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void placeOrder(@RequestBody OrderDto orderDto){
        System.out.println(orderDto.toString());
        ordersService.createOrder(orderDto);
    }

    /**
     * This method will cancel the selected order
     * @param id - Order id
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT,value = "/cancel/{id}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Orders cancelOrder(@PathVariable("id")Long id){
        return ordersService.cancelOrder(id);
    }

    /**
     * Updates the selected order by changing the values desired
     * Currently this method is used to change the order status to delivered
     * @param id - order id
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT,value = "/updateorder/{id}",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Orders updateOrder(@RequestBody OrderDto orderDto,@PathVariable("id")Long id){
        return ordersService.updateOrder(orderDto,id);
    }

    /**
     * This function is used to create a new user
     * @return - created customer values are returned
     */
    @RequestMapping(method = RequestMethod.POST,value = "/registeruser",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Customer createCustomer(@RequestBody CustomerDto customerDto){
        return customerService.createCustomer(customerDto);
    }

    /**
     * This function is used to add a new item to the db
     * @return - created item is returned
     */
    @RequestMapping(method = RequestMethod.POST,value = "/additem",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Item createItem(@RequestBody ItemDto itemDto){
        System.out.println(itemDto.getItemName()+" ->"+itemDto.getItemPrice());
        return itemService.createItem(itemDto);
    }
}
