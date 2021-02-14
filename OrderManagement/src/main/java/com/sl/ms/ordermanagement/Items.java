package com.sl.ms.ordermanagement;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Items {
	
	
	@Id
	private int itemid;
	private String name;
	private int price;
	private int quantity;
	
	public Items() {
		
	}
	
	public Items(int itemid, String name, int price, int quantity) {
		super();
		this.itemid = itemid;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	
}
