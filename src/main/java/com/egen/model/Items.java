package com.egen.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "items")
@NamedQueries({
        @NamedQuery(name="Items.findAll",
                query = "SELECT itm FROM Items itm ORDER BY itm.id ")
})
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "items_id")
    private long id;

    @Column(name = "items_name")
    private String name;

    @Column(name = "items_quantity")
    private double quantity;

    @Column(name = "items_cost")
    private double cost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id")
    public Order orders;

    public Items(String name, double quantity, double cost) {
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
    }

    public Items() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Order getOrders() {
        return orders;
    }

    public void setOrders(Order orders) {
        this.orders = orders;
    }
}
