package com.egen.ordermanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="item_name")
    private String itemName;

    @Column(name="item_price")
    private double itemPrice;

    @Column(name = "quantity_in_stock")
    private int quantityInStock;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="order_id")
    private Orders orders;

    public Item(String itemName, double itemPrice, int quantityInStock) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantityInStock = quantityInStock;
    }

    public Long getId() {
        return id;
    }

    public Item setId(Long id) {
        this.id = id;
        return this;
    }

    public String getItemName() {
        return itemName;
    }

    public Item setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public Item setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
        return this;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public Item setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
        return this;
    }

    public Item setOrders(Orders orders) {
        this.orders = orders;
        return this;
    }
}
