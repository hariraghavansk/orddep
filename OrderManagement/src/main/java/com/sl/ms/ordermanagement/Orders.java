package com.sl.ms.ordermanagement;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Orders {
	
	
	
	@Id
	private int orderid;
	private String name;
	private int total;
	
	@OneToMany(targetEntity = Items.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "orderid",referencedColumnName = "orderid")
	
	private List<Items> items;
	public Orders() {
		
	}
	public Orders(int orderid, String name, int total, List<Items> items) {
		super();
		this.orderid = orderid;
		this.name = name;
		this.total = total;
		this.items = items;
	}
	
	public List<Items> getItems() {
		return items;
	}
	public void setItems(List<Items> items) {
		this.items = items;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
		
}
