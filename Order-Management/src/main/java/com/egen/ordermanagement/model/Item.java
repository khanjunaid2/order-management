package com.egen.ordermanagement.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderItemId;

    private String pluId;

    private int quantity;

    private double amount;

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getPluId() {
        return pluId;
    }

    public void setPluId(String pluId) {
        this.pluId = pluId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Item{" +
                "orderItemId='" + orderItemId + '\'' +
                ", pluId='" + pluId + '\'' +
                ", quantity=" + quantity +
                ", amount=" + amount +
                '}';
    }
}
