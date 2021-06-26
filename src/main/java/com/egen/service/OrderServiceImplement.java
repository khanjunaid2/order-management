package com.egen.service;

//import com.egen.exception.OrderNotFoundException;
import com.egen.dto.OrderItemDTO;
import com.egen.exception.OrderNotFoundException;
import com.egen.exception.OrderServiceException;
import com.egen.exception.ParameterMissingException;
import com.egen.model.Address;
import com.egen.model.Item;
import com.egen.model.OrderItem;
import com.egen.model.Payment;
import com.egen.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.api.PodamUtils;
import uk.co.jemos.podam.typeManufacturers.IntTypeManufacturerImpl;
import uk.co.jemos.podam.typeManufacturers.TypeManufacturer;
import org.springframework.data.domain.Pageable;

//import java.time.ZonedDateTime;
//import java.awt.print.Pageable;
import java.sql.Timestamp;
import java.util.*;

@Service
public class OrderServiceImplement implements OrderService{

    public final OrderRepository repository;

    public OrderServiceImplement( OrderRepository repository) {
        this.repository = repository;
    }
    public OrderItemDTO placeOrder(OrderItemDTO orderItemDTO) {

        OrderItemDTO responseDto;
        try{
            System.out.println("Inside the Place Order function");
            OrderItem orderItem =  convertDTOtoEntity(orderItemDTO);
            OrderItem responseEntity = repository.save(orderItem);
            responseDto = convertEntitytoDTO(responseEntity);
        }catch (Exception ex){
            throw new OrderNotFoundException("");
        }

        return responseDto;
    }
    private OrderItemDTO convertEntitytoDTO(OrderItem orderItem) {
        OrderItemDTO orderItemDto = new OrderItemDTO();
        BeanUtils.copyProperties(orderItem, orderItemDto);
        return orderItemDto;
    }
    private OrderItem convertDTOtoEntity(OrderItemDTO orderItemDTO){
        OrderItem orderItem = new OrderItem();
        BeanUtils.copyProperties(orderItemDTO,orderItem);


        return orderItem;

    }

    public  List<OrderItem> getAllOrders() {
        try {
            return (List<OrderItem>) repository.findAll();
        }catch (Exception ex){
            throw new OrderServiceException("Internal Server Error occurred while fetching all orders.");
        }
    }
    public List<OrderItem> pageFindAll(Integer page,Integer size){
        try {
            Pageable pageDetails = PageRequest.of(page, size);
            List<OrderItem> allOrders = repository.findAll(pageDetails).getContent();

            return allOrders;
        }
        catch (Exception ex){
            throw new OrderServiceException("No orders found");
        }
    }
    public Optional<OrderItem> getOrderById(String id) {
//        Optional<OrderItem> order =  repository.findById(id);
//        if (order == null){
//            throw new OrderNotFoundException("Order with Order id" + id+ "is not available");
//        }
//        else{
//            return Optional.of(order.get());
//        }
        try {
            Optional<OrderItem> order =  repository.findById(id);
            return Optional.of(order.get());
        }catch (NoSuchElementException ex){
                throw new OrderNotFoundException("Order with Order id" + id+ "is not available");
        }


    }

    public  List<OrderItem> getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime) {

        if( (startTime.toString() == "" )  || (endTime.toString() == "")){
            throw new ParameterMissingException("Need Two timeStamp parameters");
        }
        try {
//        return repository.getAllOrdersWithInInterval(startTime,endTime);
            return repository.getAllOrdersWithInInterval(startTime, endTime);
        }catch (Exception ex){
            throw new OrderServiceException("Internal Server Error Occurred while fetching data");
        }
    }


    public  List<OrderItem> top10OrdersWithHighestDollarAmountInZip(String zip) {
        if(zip == ""){
            throw new ParameterMissingException("need Zipcode to proceed");
        }
        try {
            List<OrderItem> orders = (List<OrderItem>) repository.findAll();
            List<OrderItem> zipOrders = new ArrayList<>();

            for (OrderItem order : orders) {
                if (order.getShippingAddress().getZipcode().equalsIgnoreCase(zip)) {
                    zipOrders.add(order);
                }
            }
            Collections.sort(zipOrders, new Comparator<OrderItem>() {
                @Override
                public int compare(OrderItem o1, OrderItem o2) {
                    return Double.compare(o1.getTotalAmount(), o2.getTotalAmount());
                }
            });
            List<OrderItem> topOrders = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                topOrders.add(zipOrders.get(i));
            }
            return topOrders;
        }
        catch (Exception ex){
            throw new OrderServiceException("Internal Server Error");
        }
    }



//    public  OrderItem cancelOrder(OrderItem order) {
//        OrderItem oi  = repository.findById(order.getId()).get();
//        oi.setOrderStatus(OrderStatus.CANCEL);
//        return repository.save(oi);
//    }
//    public  OrderItem updateOrder(OrderItem order) {
//        return repository.save(order);
//    }

    public String createRandomOrders(int num){
        PodamFactory factory =  new PodamFactoryImpl();
        TypeManufacturer<Integer> manufacturer = new IntTypeManufacturerImpl(){

            @Override
            public Integer getInteger(AttributeMetadata attributeMetadata){
                if(attributeMetadata.getPojoClass().getName().equalsIgnoreCase("java.sql.Timestamp")){
                    return PodamUtils.getIntegerInRange(0,999);
                }
                else{
                    return super.getInteger(attributeMetadata);
                }
            }
        };
        factory.getStrategy().addOrReplaceTypeManufacturer(int.class,manufacturer);
        for(int i=0; i<num;i++){
            OrderItem orderItem = factory.manufacturePojoWithFullData(OrderItem.class);
            OrderItemDTO orderItemDTO = convertEntitytoDTO(orderItem);
            this.placeOrder(orderItemDTO);
        }
        return "success";
    }
}
