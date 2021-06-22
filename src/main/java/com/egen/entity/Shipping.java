package com.egen.entity;

import com.egen.enums.ShippingMethod;

import javax.persistence.*;

@Entity
@Table(name = "shipping")
public class Shipping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "shipping_method")
    @Enumerated(value = EnumType.STRING)
    private ShippingMethod shippingMethod;

    @Column(name = "shipping_charges")
    private double shippingCharges;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address shippingAddress;

    @OneToOne(mappedBy = "shipping")
    private Order order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShippingMethod getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(ShippingMethod shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public double getShippingCharges() {
        return shippingCharges;
    }

    public void setShippingCharges(double shippingCharges) {
        this.shippingCharges = shippingCharges;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Shipping{" +
                "id=" + id +
                ", shippingMethod=" + shippingMethod +
                ", shippingCharges=" + shippingCharges +
                ", shippingAddress=" + shippingAddress +
                ", order=" + order +
                '}';
    }
}
