package me.simran.model;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.UUID;

@Entity
public class OrderItem {
    @Id
    @Column(columnDefinition="VARCHAR(36)")
    private String itemId;
    private String itemName;
    private String itemQty;

    public OrderItem(){
        this.itemId = UUID.randomUUID().toString();
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemQty() {
        return itemQty;
    }

    public void setItemQty(String itemQty) {
        this.itemQty = itemQty;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemQty='" + itemQty + '\'' +
                '}';
    }
}
