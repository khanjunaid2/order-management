package com.egen.dtos;

public class ItemsDTO {
    private String itemId;
    private int quantity;
    private String itemName;
    private double amount;
    private ordersDTO ordersdto;

    public ItemsDTO()
    {

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

    public ordersDTO getOrdersdto() {
        return ordersdto;
    }

    public void setOrdersdto(ordersDTO ordersdto) {
        this.ordersdto = ordersdto;
    }

    @Override
    public String toString() {
        return "ItemsDTO{" +
                "itemId='" + itemId + '\'' +
                ", quantity=" + quantity +
                ", itemName='" + itemName + '\'' +
                ", amount=" + amount +
                ", ordersdto=" + ordersdto +
                '}';
    }
}
