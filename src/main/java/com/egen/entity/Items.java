package com.egen.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
public class Items {
    @Id
    private String itemId;

    private int quantity;

    private String itemName;

    private double amount;

    @ManyToOne(fetch= FetchType.LAZY)
    private orders ords;

    public Items()
    {
        this.itemId= UUID.randomUUID().toString();
    }


    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public orders getOrds() {
        return ords;
    }

    public void setOrds(orders ords) {
        this.ords = ords;
    }

    @Override
    public String toString() {
        return "Items{" +
                "itemId='" + itemId + '\'' +
                ", quantity=" + quantity +
                ", itemName='" + itemName + '\'' +
                ", amount=" + amount +
                ", ords=" + ords +
                '}';
    }
}
