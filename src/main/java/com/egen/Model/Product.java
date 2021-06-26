package com.egen.Model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product")
@Data
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "productId")
    private String productId;
    @Column(name = "productName")
    private String productName;
    @Column(name = "quantity")
    private int quantity;
    @Column(name ="tax")
    private double tax;
    @Column(name = "price")
    private double price;
    @Column(name = "subTotalWithoutTax")
    private double subTotalWithoutTax;
    @Column(name = "subTotalTax")
    private double subTotalTax;
    @Column(name = "subTotal")
    private double subTotal;

    public Product() {}

    public Product(String productId, String productName, int quantity, double tax, double price) {
        super();
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.tax = tax;
        this.subTotalWithoutTax = this.price * this.quantity;
        this.subTotalTax = this.tax / 100 * this.subTotalWithoutTax;
        this.subTotal = this.subTotalTax  + this.subTotalWithoutTax;
    }

    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.subTotalWithoutTax = this.price * this.quantity;
        this.subTotalTax = this.tax / 100 * this.subTotalWithoutTax;
        this.subTotal = this.subTotalTax  + this.subTotalWithoutTax;
    }
    public double getTax() {
        return tax;
    }
    public void setTax(double tax) {
        this.tax = tax;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
        this.subTotalWithoutTax = this.price * this.quantity;
        this.subTotalTax = this.subTotalWithoutTax + this.tax / 100 * this.subTotalWithoutTax;
    }
    public double getSubTotalWithoutTax() {
        return subTotalWithoutTax;
    }
    public double getSubtotalTax() {
        return subTotalTax;
    }
    public double getSubTotal() {
        return subTotal;
    }

    @Override
    public String toString() {
        return "product [productId=" + productId + ", productName=" + productName + ", quantity=" + quantity + ", price=" + price
                + ", subtotal=" + subTotal + "]";
    }
}
