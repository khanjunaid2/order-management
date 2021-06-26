package com.egen.ordermanagement.dto;

import com.egen.ordermanagement.enums.OrderStatus;
import com.egen.ordermanagement.enums.ShipmentMethod;
import com.egen.ordermanagement.model.Address;
import com.egen.ordermanagement.model.Payment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto implements Serializable {

    private long id;
    @JsonProperty(value = "customerId")
    private Long customerId;
    private long[] items;
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

    public OrderDto setId(long id) {
        this.id = id;
        return this;
    }

    public OrderDto setCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }

    public OrderDto setItems(long[] items) {
        this.items = items;
        return this;
    }

    public OrderDto setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
        return this;
    }

    public OrderDto setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
        return this;
    }

    public OrderDto setBillingSameAsShippingAddress(boolean billingSameAsShippingAddress) {
        this.billingSameAsShippingAddress = billingSameAsShippingAddress;
        return this;
    }

    public OrderDto setPayments(List<Payment> payments) {
        this.payments = payments;
        return this;
    }

    public OrderDto setShipmentMethod(ShipmentMethod shipmentMethod) {
        this.shipmentMethod = shipmentMethod;
        return this;
    }

    public OrderDto setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
        return this;
    }

    public OrderDto setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }
}
