package com.egen.passport.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "item")
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "pluId")
    private String pluId;

    @Column(name = "quantity")
    public int quantity;

    @Column(name = "amount")
    public double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "order_id")
    @JsonBackReference
    public CustomerOrder order;

    public Item() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPluId(String pluId) {
        this.pluId = pluId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public String getPluId() {
        return pluId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setOrder(CustomerOrder order) {
        this.order = order;
    }

    public CustomerOrder getOrder() {
        return order;
    }
}
