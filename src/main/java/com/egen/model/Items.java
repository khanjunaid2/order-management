package com.egen.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;
import com.egen.model.Order;

@Entity
public class Items {

    @Id
    private String id;
    private String item_name;
    private double item_price;
    private int quantity_in_stock;
    @ManyToOne
    @JoinColumn(name = "id")
    private Order order;

    public Items(){
        this.id = UUID.randomUUID().toString();
    }

    public Items(String id, String item_name, double item_price, int quantity_in_stock, Order order) {
        this.id = id;
        this.item_name = item_name;
        this.item_price = item_price;
        this.quantity_in_stock = quantity_in_stock;
        this.order = order;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public double getItem_price() {
        return item_price;
    }

    public void setItem_price(double item_price) {
        this.item_price = item_price;
    }

    public int getQuantity_in_stock() {
        return quantity_in_stock;
    }

    public void setQuantity_in_stock(int quantity_in_stock) {
        this.quantity_in_stock = quantity_in_stock;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
