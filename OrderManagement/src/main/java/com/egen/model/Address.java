package com.egen.model;

import java.time.ZonedDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.egen.enums.AddressType;

@Entity
public class Address {
	
	@Id
	@Column
	private String addressId;
	
	@Column
	private AddressType addressType;

	@Column
	private String addressLine1;
	
	@Column
	private String addressLine2;
	
	@Column
	private String addressCity;
	
	@Column
	private String addressState;
	
	@Column
	private String addressZip;
	
	@Column
	private ZonedDateTime billingAddressCreatedOn;
	
	@Column
	private ZonedDateTime billingAddressModifiedOn;

	public Address(AddressType addressType, String addressLine1, String addressLine2, String addressCity,
			String addressState, String addressZip, ZonedDateTime billingAddressCreatedOn,
			ZonedDateTime billingAddressModifiedOn) {
		this.addressId = UUID.randomUUID().toString();
		this.addressType = addressType;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.addressCity = addressCity;
		this.addressState = addressState;
		this.addressZip = addressZip;
		this.billingAddressCreatedOn = billingAddressCreatedOn;
		this.billingAddressModifiedOn = billingAddressModifiedOn;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public AddressType getAddressType() {
		return addressType;
	}

	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getAddressState() {
		return addressState;
	}

	public void setAddressState(String addressState) {
		this.addressState = addressState;
	}

	public String getAddressZip() {
		return addressZip;
	}

	public void setAddressZip(String addressZip) {
		this.addressZip = addressZip;
	}

	public ZonedDateTime getBillingAddressCreatedOn() {
		return billingAddressCreatedOn;
	}

	public void setBillingAddressCreatedOn(ZonedDateTime billingAddressCreatedOn) {
		this.billingAddressCreatedOn = billingAddressCreatedOn;
	}

	public ZonedDateTime getBillingAddressModifiedOn() {
		return billingAddressModifiedOn;
	}

	public void setBillingAddressModifiedOn(ZonedDateTime billingAddressModifiedOn) {
		this.billingAddressModifiedOn = billingAddressModifiedOn;
	}

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", addressType=" + addressType + ", addressLine1=" + addressLine1
				+ ", addressLine2=" + addressLine2 + ", addressCity=" + addressCity + ", addressState=" + addressState
				+ ", addressZip=" + addressZip + ", billingAddressCreatedOn=" + billingAddressCreatedOn
				+ ", billingAddressModifiedOn=" + billingAddressModifiedOn + "]";
	}
	

}
