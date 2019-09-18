package com.training.ordmgmtprojcorejava.model;

public class Item {

	private int itemId;
	private String itemName;
	private float itemPrice;
	private String itemDescription;
	private boolean isOnSale;
	private int discount;
	private int quantity;
	private float itemTotalPrice;

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public float getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(float itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public boolean isOnSale() {
		return isOnSale;
	}

	public void setOnSale(boolean isOnSale) {
		this.isOnSale = isOnSale;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;	
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getItemTotalPrice() {
		return itemTotalPrice;
	}

	public void setItemTotalPrice(float itemTotalPrice) {
		this.itemTotalPrice = itemTotalPrice;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", itemDescription="
				+ itemDescription + ", isOnSale=" + isOnSale + ", discount=" + discount + ", quantity=" + quantity
				+ ", itemTotalPrice=" + itemTotalPrice + "]";
	}	
	
}
