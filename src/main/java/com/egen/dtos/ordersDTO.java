package com.egen.dtos;

import com.egen.enums.DeliveryType;
import com.egen.enums.OrderStatus;

import java.util.List;

public class ordersDTO {
    private String id;

    private double total;
    private String customerId;
    private PaymentDTO paymentDetails;
    private List<ItemsDTO> itemsList;
    private List<AddressDTO> addresses;

    private DeliveryType type;
    private OrderStatus status;

    public ordersDTO(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public PaymentDTO getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDTO paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public List<ItemsDTO> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<ItemsDTO> itemsList) {
        this.itemsList = itemsList;
    }

    public List<AddressDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressDTO> addresses) {
        this.addresses = addresses;
    }

    public DeliveryType getType() {
        return type;
    }

    public void setType(DeliveryType type) {
        this.type = type;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ordersDTO{" +
                "id='" + id + '\'' +
                ", total=" + total +
                ", customerId='" + customerId + '\'' +
                ", paymentDetails=" + paymentDetails +
                ", itemsList=" + itemsList +
                ", addresses=" + addresses +
                ", type=" + type +
                ", status=" + status +
                '}';
    }
}
