package com.egen.passport.demo.controller.rest;


import com.egen.passport.demo.dto.CustomerDTO;
import com.egen.passport.demo.dto.ItemDTO;
import com.egen.passport.demo.dto.OrderDTO;
import com.egen.passport.demo.entity.*;
import com.egen.passport.demo.repository.CustomerRepository;
import com.egen.passport.demo.repository.OrderRepository;
import com.egen.passport.demo.response.Response;
import com.egen.passport.demo.response.ResponseMetadata;
import com.egen.passport.demo.response.StatusMessage;
import com.egen.passport.demo.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository repository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderService orderService;

    @GetMapping("/bulkcreate")
    public String bulkcreate() {
// save a single Customer
        //  repository.save(new Customer("Rajesh", "Bhojwani"));

// save a list of Customers
       /* repository.saveAll(Arrays.asList(new Customer("Salim", "Khan")
                , new Customer("Rajesh", "Parihar")
                , new Customer("Rahul", "Dravid")
                , new Customer("Dharmendra", "Bhojwani")));*/

        return "Customers are created";
    }

    /*@PostMapping("/create")
    public String create(@RequestBody CustomerDTO customer){
        repository.save(new Customer(customer.getFirstName(), customer.getLastName()));
        return "Customer is created";
    }*/

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public Response<String> create(@RequestBody OrderDTO order) {
        return orderService.createOrder(order) == Boolean.TRUE ?
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.SUCCESS.name()).build())
                        .data("Order Created")
                        .build()
                :
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                        .data("Order Not Created")
                        .build();
    }

    @PostMapping(value = "/createOrders", consumes = "application/json", produces = "application/json")
    public Response<String> createOrders(@RequestBody List<OrderDTO> order) {
        return orderService.createOrders(order) == Boolean.TRUE ?
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.SUCCESS.name()).build())
                        .data("Order Created")
                        .build()
                :
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                        .data("Order Not Created")
                        .build();
    }

    @GetMapping(value = "/getOrderById", consumes = "application/json", produces = "application/json")
    public Response<CustomerOrder> getOrderById(@RequestBody Long id) {
        return Response.<CustomerOrder>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data((orderService.getOrderById(id)))
                .build();
    }

  /*  public CustomerOrder copyProperties(OrderDTO srcOrderDto , CustomerOrder des){
        des.setCustomerId(srcOrderDto.getCustomerId());
        des.setBillingAddressSame(srcOrderDto.isBillingAddressSame());
        des.setShippingAddress(srcOrderDto.getShippingAddress());
        des.setTax(srcOrderDto.getTax());
        des.setTotalAmount(srcOrderDto.getTotalAmount());
        des.setOrderStatus(srcOrderDto.getOrderStatus());


    }*/


   /* @GetMapping("/findall")
    public List<CustomerDTO> findAll(){

        List<Customer> customers = repository.findAll();
        List<CustomerDTO> customerUI = new ArrayList<>();


        for (Customer customer : customers) {

            customerUI.add(new CustomerDTO(customer.,customer.getLastName()));
        }

        return customerUI;
    }*/

    @RequestMapping("/search/{id}")
    public CustomerDTO search(@PathVariable long id) {
        Optional<Customer> customer = repository.findById(id);
        repository.saveAndFlush(new Customer());


        CustomerDTO dto = new CustomerDTO();
        if (customer.isPresent()) {
            BeanUtils.copyProperties(customer.get(), dto);
            return dto;
        }
        return null;
    }

   /* @RequestMapping("/searchbyfirstname/{firstname}")
    public List<CustomerUI> fetchDataByFirstName(@PathVariable String firstname){

        List<Customer> customers = repository.findByFirstName(firstname);
        List<CustomerUI> customerUI = new ArrayList<>();
        for (Customer customer : customers) {
            customerUI.add(new CustomerUI(customer.getFirstName(),customer.getLastName()));
        }
        return customerUI;
    }*/
}
