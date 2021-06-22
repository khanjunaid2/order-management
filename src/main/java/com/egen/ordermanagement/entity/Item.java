package com.egen.ordermanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Item {

    @Id
    @Column(columnDefinition="VARCHAR(36)")
    private String itemId;

    private String itemName;
    private BigDecimal itemQty;
    private BigDecimal total;

    public Item(){
        this.itemId = UUID.randomUUID().toString();
    }

    public Item(String itemName, BigDecimal itemQty, BigDecimal total) {
        this.itemName = itemName;
        this.itemQty = itemQty;
        this.total = total;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getItemQty() {
        return itemQty;
    }

    public void setItemQty(BigDecimal itemQty) {
        this.itemQty = itemQty;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemQty=" + itemQty +
                ", total=" + total +
                '}';
    }
}
