package com.egen.ordermanagment.dto;

import com.egen.ordermanagment.model.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrdersDTO{

    private String Id;
    private String customerId;
    private Double orderSubTotal;
    private Double orderTotal;
    private Double orderTax;
    private Timestamp orderCreated;
    private Timestamp orderModified;
    private OrderStatus orderStatus;
    private ShippingType shipmentType;
    private List<OrderItems> orderItemsList;
    private Payment paymentDetail;
    private Address shippingAddress ;

}