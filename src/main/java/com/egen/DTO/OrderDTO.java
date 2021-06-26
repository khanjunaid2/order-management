package com.egen.DTO;

import com.egen.Model.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDTO implements Serializable {

    private long Id;
    @JsonProperty(value = "OrderId")
    private String orderId;
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

    public long getId() {
        return Id;
    }
    public void setId(long id) {
        Id = id;
    }

    public void setId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getOrder_customer_id() {
        return order_customer_id;
    }

    public void setOrder_customer_id(String order_customer_id) {
        this.order_customer_id = order_customer_id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getOrder_subtotal() {
        return order_subtotal;
    }

    public void setOrder_subtotal(double order_subtotal) {
        this.order_subtotal = order_subtotal;
    }

    public double getOrder_tax() {
        return order_tax;
    }

    public void setOrder_tax(double order_tax) {
        this.order_tax = order_tax;
    }

    public double getOrder_total() {
        return order_total;
    }

    public void setOrder_total(double order_total) {
        this.order_total = order_total;
    }

    public ZonedDateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(ZonedDateTime created_date) {
        this.created_date = created_date;
    }

    public ZonedDateTime getModified_date() {
        return modified_date;
    }

    public void setModified_date(ZonedDateTime modified_date) {
        this.modified_date = modified_date;
    }

    public List<Payment> getPayment() {
        return payment;
    }

    public void setPayment(List<Payment> payment) {
        this.payment = payment;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public Address getOrder_billing_address() {
        return order_billing_address;
    }

    public void setOrder_billing_address(Address order_billing_address) {
        this.order_billing_address = order_billing_address;
    }

    public Address getOrder_shipping_address() {
        return order_shipping_address;
    }

    public void setOrder_shipping_address(Address order_shipping_address) {
        this.order_shipping_address = order_shipping_address;
    }
}
