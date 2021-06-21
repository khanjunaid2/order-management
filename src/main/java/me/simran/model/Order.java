package me.simran.model;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="orders")

public class Order {
    public enum Stats {
        ORDERED,
        TRANSIT,
        DELIVERED
    }
    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    private String orderID;

    @Enumerated(EnumType.STRING)
    private Stats orderStatus;

    private java.sql.Date dateCreated;
    private java.sql.Date dateModified;
    private String shippingMethod;

    /**One order can have only one Shipping Address*/
    @OneToOne
    private ShippingAddress shipping;

    /**One order can have one customer only*/
    @OneToOne
    private Customer customer;

    /** One order can have many items*/
    @OneToMany
    private List<OrderItem> orderItem;

    /**One Order can have only one payment summary*/
    @OneToOne
    private Payment payment;

    public Order(){

    }

    public Order(String id){
        this.orderID = id;
    }
    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Stats getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Stats orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(java.sql.Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(java.sql.Date dateModified) {
        this.dateModified = dateModified;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }


    public ShippingAddress getShipping() {
        return shipping;
    }

    public void setShipping(ShippingAddress shipping) {
        this.shipping = shipping;
    }

    public List<OrderItem> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(List<OrderItem> orderItem) {
        this.orderItem = orderItem;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", orderStatus=" + orderStatus +
                ", dateCreated=" + dateCreated +
                ", dateModified=" + dateModified +
                ", shippingMethod='" + shippingMethod + '\'' +
                ", shipping=" + shipping +
                ", customer=" + customer +
                ", orderItem=" + orderItem +
                ", payment=" + payment +
                '}';
    }
}
