package com.egen.passport.demo.dto;

import com.egen.passport.demo.entity.DeliveryType;
import com.egen.passport.demo.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDTO implements Serializable {

    private long id;
    @JsonProperty(value = "orderStatus")
    private OrderStatus orderStatus;
    @JsonProperty(value = "customerId")
    private String customerId;
    @JsonProperty(value = "subTotal")
    private double subTotal;
    @JsonProperty(value = "tax")
    private double tax;
    @JsonProperty(value = "totalAmount")
    private double totalAmount;
    @JsonProperty(value = "addresses")
    private List<AddressDTO> addresses;
    @JsonProperty(value = "isBillingAddressSame")
    private boolean isBillingAddressSame;

    private DeliveryType deliveryType;
    private List<ItemDTO> items;
    private PaymentDTO paymentDetails;

    public OrderDTO() {
    }

    public long getId() {
        return id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public String getCustomerId() {
        return customerId;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public double getTax() {
        return tax;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public boolean isBillingAddressSame() {
        return isBillingAddressSame;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }


    public void setBillingAddressSame(boolean billingAddressSame) {
        isBillingAddressSame = billingAddressSame;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

    public PaymentDTO getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDTO paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public List<AddressDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressDTO> addresses) {
        this.addresses = addresses;
    }
}
