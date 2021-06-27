package com.egen.model;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import com.egen.enums.OrderStatus;

@Entity
@Table(name = "orders")
public class Order {
    
	@Id
	@Column
	private String orderId;
	
	@Column
	private OrderStatus orderStatus;

	@OneToOne
	private Customer orderCustomer;
	
	@OneToMany
	private List<Item> orderItems;
	
	@Column
	private double orderSubtotal;
	
	@Column
	private double orderTax;
	
	@Column
	private double orderShippingCharges;
	
	@Column
	private double orderTotal;
	
	@OneToMany
	private List<Payment> orderPayments;

	@OneToOne
	private Address orderBillingAddress;

	@OneToOne
	private Address orderShippingAddress;
	
	@Column
	private ZonedDateTime orderCreatedOn;
	
	@Column
	private ZonedDateTime orderModifiedOn;
	
	@Column
	private ZonedDateTime orderCompletedOn;
	
	public Order(String id) {
		this.orderId = id;
	}

	public Order(OrderStatus orderStatus, Customer orderCustomer, List<Item> orderItems, double orderSubtotal,
				 double orderTax, double orderShippingCharges, double orderTotal, List<Payment> orderPayments,
				 Address orderBillingAddress, Address orderShippingAddress, ZonedDateTime orderCreatedOn,
				 ZonedDateTime orderModifiedOn, ZonedDateTime orderCompletedOn) {
		this.orderId = UUID.randomUUID().toString();
		this.orderStatus = orderStatus;
		this.orderCustomer = orderCustomer;
		this.orderItems = orderItems;
		this.orderSubtotal = orderSubtotal;
		this.orderTax = orderTax;
		this.orderShippingCharges = orderShippingCharges;
		this.orderTotal = orderTotal;
		this.orderPayments = orderPayments;
		this.orderBillingAddress = orderBillingAddress;
		this.orderShippingAddress = orderShippingAddress;
		this.orderCreatedOn = orderCreatedOn;
		this.orderModifiedOn = orderModifiedOn;
		this.orderCompletedOn = orderCompletedOn;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Customer getOrderCustomer() {
		return orderCustomer;
	}

	public void setOrderCustomer(Customer orderCustomer) {
		this.orderCustomer = orderCustomer;
	}

	public List<Item> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<Item> orderItems) {
		this.orderItems = orderItems;
	}

	public double getOrderSubtotal() {
		return orderSubtotal;
	}

	public void setOrderSubtotal(double orderSubtotal) {
		this.orderSubtotal = orderSubtotal;
	}

	public double getOrderTax() {
		return orderTax;
	}

	public void setOrderTax(double orderTax) {
		this.orderTax = orderTax;
	}

	public double getOrderShippingCharges() {
		return orderShippingCharges;
	}

	public void setOrderShippingCharges(double orderShippingCharges) {
		this.orderShippingCharges = orderShippingCharges;
	}

	public double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public List<Payment> getOrderPayments() {
		return orderPayments;
	}

	public void setOrderPayments(List<Payment> orderPayments) {
		this.orderPayments = orderPayments;
	}

	public Address getOrderBillingAddress() {
		return orderBillingAddress;
	}

	public void setOrderBillingAddress(Address orderBillingAddress) {
		this.orderBillingAddress = orderBillingAddress;
	}

	public Address getOrderShippingAddress() {
		return orderShippingAddress;
	}

	public void setOrderShippingAddress(Address orderShippingAddress) {
		this.orderShippingAddress = orderShippingAddress;
	}

	public ZonedDateTime getOrderCreatedOn() {
		return orderCreatedOn;
	}

	public void setOrderCreatedOn(ZonedDateTime orderCreatedOn) {
		this.orderCreatedOn = orderCreatedOn;
	}

	public ZonedDateTime getOrderModifiedOn() {
		return orderModifiedOn;
	}

	public void setOrderModifiedOn(ZonedDateTime orderModifiedOn) {
		this.orderModifiedOn = orderModifiedOn;
	}

	public ZonedDateTime getOrderCompletedOn() {
		return orderCompletedOn;
	}

	public void setOrderCompletedOn(ZonedDateTime orderCompletedOn) {
		this.orderCompletedOn = orderCompletedOn;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderStatus=" + orderStatus + ", orderCustomer=" + orderCustomer
				+ ", orderItems=" + orderItems + ", orderSubtotal=" + orderSubtotal + ", orderTax=" + orderTax
				+ ", orderShippingCharges=" + orderShippingCharges + ", orderTotal=" + orderTotal + ", orderPayments="
				+ orderPayments + ", orderBillingAddress=" + orderBillingAddress + ", orderShippingAddress="
				+ orderShippingAddress + ", orderCreatedOn=" + orderCreatedOn + ", orderModifiedOn=" + orderModifiedOn
				+ ", orderCompletedOn=" + orderCompletedOn + "]";
	}
	
}
