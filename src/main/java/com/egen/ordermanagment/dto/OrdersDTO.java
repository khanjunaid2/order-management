package com.egen.ordermanagment.dto;

import com.egen.ordermanagment.model.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
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
    private List<OrderItemsDTO> orderItemsList;
    private PaymentDTO paymentDetail;
    private AddressDTO shippingAddress ;

}