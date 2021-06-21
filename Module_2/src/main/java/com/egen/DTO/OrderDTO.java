package com.egen.DTO;

import com.egen.Model.*;
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
public class OrderDTO implements Serializable {

    private long id;
    @JsonProperty(value = "customerId")
    private Long customerId;
    private int[] products;
    @JsonProperty(value = "shippingAddress")
    private Address shippingAddress;
    @JsonProperty(value = "billingAddress")
    private Address billingAddress;
    @JsonProperty(value = "billingSameAsShippingAddress")
    private boolean billingSameAsShippingAddress;
    private List<Payment> payments;
    @JsonProperty(value = "shipping")
    private Shipping shipping;
    @JsonProperty(value = "productQuantity")
    private int productQuantity;
    @JsonProperty(value="orderStatus")
    private OrderStatus orderStatus;
}
