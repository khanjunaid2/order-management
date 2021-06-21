package com.egen.ordermanagment.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class OrderItems {

    @Id
    private String order_item_id;
    private String order_item_name;
    private int order_item_qty;
    private Double order_item_unit_price;

    public OrderItems(){
        this.order_item_id = UUID.randomUUID().toString();
    }

    public String getOrder_item_id() {
        return order_item_id;
    }

    public void setOrder_item_id(String order_item_id) {
        this.order_item_id = order_item_id;
    }

    public String getOrder_item_name() {
        return order_item_name;
    }

    public void setOrder_item_name(String order_item_name) {
        this.order_item_name = order_item_name;
    }

    public int getOrder_item_qty() {
        return order_item_qty;
    }

    public void setOrder_item_qty(int order_item_qty) {
        this.order_item_qty = order_item_qty;
    }

    public Double getOrder_item_unit_price() {
        return order_item_unit_price;
    }

    public void setOrder_item_unit_price(Double order_item_unit_price) {
        this.order_item_unit_price = order_item_unit_price;
    }

    @Override
    public String toString() {
        return "OrderItems{" +
                "order_item_id='" + order_item_id + '\'' +
                ", order_item_name='" + order_item_name + '\'' +
                ", order_item_qty=" + order_item_qty +
                ", order_item_unit_price=" + order_item_unit_price +
                '}';
    }
}
