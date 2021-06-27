package com.egen.model;

import java.time.ZonedDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Item {
	
	@Id
	@Column
	private String itemId;
	
	@Column
	private String itemName;
	
	@Column
	private String itemQty;
	
	@Column
	private double itemUnitPrice;
	
	@Column
	private double itemTotal;
	
	@Column
	private ZonedDateTime itemCreatedOn;
	
	@Column
	private ZonedDateTime itemModifiedOn;

	public Item(String itemName, String itemQty, double itemUnitPrice, double itemTotal, ZonedDateTime itemCreatedOn,
			ZonedDateTime itemModifiedOn) {
		this.itemId = UUID.randomUUID().toString();
		this.itemName = itemName;
		this.itemQty = itemQty;
		this.itemUnitPrice = itemUnitPrice;
		this.itemTotal = itemTotal;
		this.itemCreatedOn = itemCreatedOn;
		this.itemModifiedOn = itemModifiedOn;
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

	public double getItemUnitPrice() {
		return itemUnitPrice;
	}

	public void setItemUnitPrice(double itemUnitPrice) {
		this.itemUnitPrice = itemUnitPrice;
	}

	public double getItemTotal() {
		return itemTotal;
	}

	public void setItemTotal(double itemTotal) {
		this.itemTotal = itemTotal;
	}

	public ZonedDateTime getItemCreatedOn() {
		return itemCreatedOn;
	}

	public void setItemCreatedOn(ZonedDateTime itemCreatedOn) {
		this.itemCreatedOn = itemCreatedOn;
	}

	public ZonedDateTime getItemModifiedOn() {
		return itemModifiedOn;
	}

	public void setItemModifiedOn(ZonedDateTime itemModifiedOn) {
		this.itemModifiedOn = itemModifiedOn;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + ", itemQty=" + itemQty + ", itemUnitPrice="
				+ itemUnitPrice + ", itemTotal=" + itemTotal + ", itemCreatedOn=" + itemCreatedOn + ", itemModifiedOn="
				+ itemModifiedOn + "]";
	}

}
