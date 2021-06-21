package com.egen.ordermanagement.dto;

import com.egen.ordermanagement.enums.OrderStatus;
import com.egen.ordermanagement.enums.ShipmentMethod;
import com.egen.ordermanagement.model.Address;
import com.egen.ordermanagement.model.Payment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto implements Serializable {

    private long id;
    @JsonProperty(value = "customerId")
    private Long customerId;
    private int[] items;
    @JsonProperty(value = "shippingAddress")
    private Address shippingAddress;
    @JsonProperty(value = "billingAddress")
    private Address billingAddress;
    @JsonProperty(value = "billingSameAsShippingAddress")
    private boolean billingSameAsShippingAddress;
    private List<Payment> payments;
    @JsonProperty(value = "shipmentMethod")
    private ShipmentMethod shipmentMethod;
    @JsonProperty(value = "itemQuantity")
    private int itemQuantity;
    @JsonProperty(value="orderStatus")
    private OrderStatus orderStatus;
}
