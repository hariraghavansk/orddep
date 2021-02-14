package com.sl.ms.ordermanagement;

public class OrderRequest {
	
	public OrderRequest() {
		
	}

	public OrderRequest(Orders orders) {
		super();
		this.orders = orders;
	}

	private Orders orders;

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

}
