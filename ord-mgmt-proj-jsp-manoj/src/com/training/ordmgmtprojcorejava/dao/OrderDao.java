package com.training.ordmgmtprojcorejava.dao;

import java.util.List;

import com.training.ordmgmtprojcorejava.model.Item;
import com.training.ordmgmtprojcorejava.model.Order;

public interface OrderDao {
	abstract public List<Item> getCartItems();  
	
	
	
	public abstract void insertOrderDetails(Order order, long customerId);
	public abstract void insertOrderItem(Order order);
	public abstract long getOrderSequenceId();
	
	abstract public boolean clearCartItems();
	
	abstract public Order getOrderByOrderId(Long orderId);
	
	abstract public boolean deleteOrder(Long orderId);
}
