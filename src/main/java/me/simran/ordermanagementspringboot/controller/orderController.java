package me.simran.ordermanagementspringboot.controller;

import io.swagger.annotations.*;
import me.simran.ordermanagementspringboot.dto.OrderDTO;
import me.simran.ordermanagementspringboot.entity.Order;
import me.simran.ordermanagementspringboot.entity.OrderPage;
import me.simran.ordermanagementspringboot.exception.response.Response;
import me.simran.ordermanagementspringboot.exception.response.ResponseMetadata;
import me.simran.ordermanagementspringboot.exception.response.StatusMessage;
import me.simran.ordermanagementspringboot.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags={"Order Controller"})
@SwaggerDefinition(tags={
        @Tag(name="Order Controller", description="API Description for Online Orders")
})
public class orderController {
    OrderService service;

    public orderController(OrderService service){
        this.service = service;
    }

    @GetMapping(value="/getAllOrders", produces="application/json")
    @ApiOperation(value="Find All Orders",
                    notes="Returns the List of all orders from the database")
    public Response<Page<Order>> getAllOrders(OrderPage orderPage){
        return Response.<Page<Order>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data((Page<Order>) service.getAllOrders(orderPage))
                .build();
    }

    @PostMapping(value="/createOrder", consumes="application/json")
    @ApiOperation(value="Creates an Order",
            notes="Creates an Order to be Stored in the Database ")
    public Response<String> createOrder(@RequestBody OrderDTO order){
        return service.createOrder(order) == Boolean.TRUE?
                Response.<String>builder()
                    .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data("Order has been Created!")
                .build()
                :
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(400)
                                .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                        .data("Order Not Created")
                        .build();
    }

    @PostMapping(value="/createOrders", consumes="application/json")
    @ApiOperation(value="Creates Multiple Orders",
            notes="Creates Multiple Orders together to be stored in the Database at once")
    public Response<String> createOrders(@RequestBody List<OrderDTO> order){
        return service.createOrders(order) == Boolean.TRUE?
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.SUCCESS.name()).build())
                        .data("Orders have been Created!")
                        .build()
                :
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(400)
                                .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                        .data("Orders Not Created")
                        .build();
    }

    @GetMapping(value="/getOrderById", produces="application/json")
    @ApiOperation(value="Find an Order By Id",
            notes="Returns the Order mapped by the request Id")
    public Response<Order> getOrderById(@ApiParam(value="Id of the Order", required=true) @RequestBody Long id){
        return Response.<Order>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(service.getOrderById(id))
                .build();
    }

    @PutMapping(value="/update", consumes="application/json")
    @ApiOperation(value="Updates an Order",
            notes="Updates a Particular order of a given Id")
    public Response<String> update(@RequestBody OrderDTO order, @ApiParam(value="Id of the Order", required=true) @RequestBody Long id){
        return service.update(order, id) == Boolean.TRUE?
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.SUCCESS.name()).build())
                        .data("Order has been Updated!")
                        .build()
                :
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(400)
                                .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                        .data("Order Not Updated")
                        .build();
    }

    @DeleteMapping(value="/delete")
    @ApiOperation(value="Deleting an Order by ID",
            notes="Deletes a particular order by a given ID")
    public Response<String> delete(@ApiParam(value="Id of the Order", required=true) @RequestBody Long id){
        return service.delete(id) == Boolean.TRUE?
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.SUCCESS.name()).build())
                        .data("Order has been Deleted!")
                        .build()
                :
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                        .data("Order Not Deleted")
                        .build();
    }
}
