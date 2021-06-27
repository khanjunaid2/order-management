package com.egen.model;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.egen.enums.Gender;

@Entity
public class Customer {
	
	@Id
	@Column
	private String customerId;
	
	@Column
	private String customerFirstName;
	
	@Column
	private String customerLastName;
	
	@Column
	private Gender customerGender;
	
	@Column
	private String customerEmail;
	
	@Column
	private String customerPhoneNumber;
	
	@Column
	@OneToMany
	private List<Address> customerAddress;
	
	@Column
	private ZonedDateTime customerCreatedOn;
	
	@Column
	private ZonedDateTime customerModifiedOn;

	public Customer(String customerFirstName, String customerLastName, Gender customerGender, String customerEmail,
			String customerPhoneNumber, List<Address> customerAddress, ZonedDateTime customerCreatedOn,
			ZonedDateTime customerModifiedOn) {
		this.customerId = UUID.randomUUID().toString();
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
		this.customerGender = customerGender;
		this.customerEmail = customerEmail;
		this.customerPhoneNumber = customerPhoneNumber;
		this.customerAddress = customerAddress;
		this.customerCreatedOn = customerCreatedOn;
		this.customerModifiedOn = customerModifiedOn;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public Gender getCustomerGender() {
		return customerGender;
	}

	public void setCustomerGender(Gender customerGender) {
		this.customerGender = customerGender;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public List<Address> getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(List<Address> customerAddress) {
		this.customerAddress = customerAddress;
	}

	public ZonedDateTime getCustomerCreatedOn() {
		return customerCreatedOn;
	}

	public void setCustomerCreatedOn(ZonedDateTime customerCreatedOn) {
		this.customerCreatedOn = customerCreatedOn;
	}

	public ZonedDateTime getCustomerModifiedOn() {
		return customerModifiedOn;
	}

	public void setCustomerModifiedOn(ZonedDateTime customerModifiedOn) {
		this.customerModifiedOn = customerModifiedOn;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerFirstName=" + customerFirstName + ", customerLastName="
				+ customerLastName + ", customerGender=" + customerGender + ", customerEmail=" + customerEmail
				+ ", customerPhoneNumber=" + customerPhoneNumber + ", customerAddress=" + customerAddress
				+ ", customerCreatedOn=" + customerCreatedOn + ", customerModifiedOn=" + customerModifiedOn + "]";
	}

}
