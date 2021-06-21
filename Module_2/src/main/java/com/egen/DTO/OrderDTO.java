package com.egen.DTO;

import com.egen.Model.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDTO implements Serializable {

    @JsonProperty(value = "OrderId")
    private String id;
    @JsonProperty(value = "OrderStatus")
    private OrderStatus status;
    @JsonProperty(value = "CustomerId")
    private String order_customer_id;
    private List<Product> products;
    @JsonProperty(value = "OrderSubTotal")
    private double order_subtotal;
    @JsonProperty(value = "OrderTax")
    private double order_tax;
    @JsonProperty(value = "OrderTotal")
    private double order_total;
    @JsonProperty(value = "CreatedDate")
    private ZonedDateTime created_date;
    @JsonProperty(value = "ModifiedDate")
    private ZonedDateTime modified_date;
    private List<Payment> payment;
    @JsonProperty(value = "shipping")
    private Shipping shipping;
    @JsonProperty(value = "billingAddress")
    private Address order_billing_address;
    @JsonProperty(value = "shippingAddress")
    private Address order_shipping_address;
}
