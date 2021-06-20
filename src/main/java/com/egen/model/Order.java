package com.egen.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "orders")
@NamedQueries({
        @NamedQuery(name="Order.findAll",
                    query = "SELECT ord FROM Order ord ORDER BY ord.id ")
})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orders_id")
    private long id;

    @Column(name = "orders_status")
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id",referencedColumnName = "customer_id")
    public Customer customer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orders")
    public List<Items> items;

    @Column(name = "orders_subtotal")
    private double subtotal;

    @Column(name = "orders_tax")
    private double tax;

    @Column(name = "orders_shipping_charges")
    private double shippingCharges;

    @Column(name = "orders_total")
    private double total;

    @Column(name = "order_created_date")
    private Timestamp createdDate;

    @Column(name = "orders_modified_date")
    private Timestamp modifiedDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orders")
    public List<Payment> payment;

    @Column(name = "orders_shipping_method")
    private ShippingMethod shippingMethod;

    @OneToOne(fetch = FetchType.LAZY)
    public Address shippingAddress;

    public Order(){}

    public Order(OrderStatus status, List<Items> items, double tax, double shippingCharges, Timestamp createdDate, Timestamp modifiedDate, List<Payment> payment, ShippingMethod shippingMethod, Address shippingAddress) {
        this.status = status;
        this.items = items;
        this.tax = tax;
        this.shippingCharges = shippingCharges;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.payment = payment;
        this.shippingMethod = shippingMethod;
        this.shippingAddress = shippingAddress;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public double getSubtotal() {
        double subtotal=0.0d;
        List<Items> items=getItems();
        for(Items item: items) {
            subtotal=subtotal + ((item.getCost())* (item.getQuantity()));
        }
        return subtotal;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getShippingCharges() {
        return shippingCharges;
    }

    public void setShippingCharges(double shippingCharges) {
        this.shippingCharges = shippingCharges;
    }

    public double getTotal() {
        double total=getSubtotal()+getTax()+getShippingCharges();
        return total;
    }
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public List<Payment> getPayment() {
        return payment;
    }

    public void setPayment(List<Payment> payment) {
        this.payment = payment;
    }

    public ShippingMethod getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(ShippingMethod shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


}
