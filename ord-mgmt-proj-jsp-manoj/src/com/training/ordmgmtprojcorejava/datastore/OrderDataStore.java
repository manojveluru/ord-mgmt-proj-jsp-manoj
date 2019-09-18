package com.training.ordmgmtprojcorejava.datastore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.training.ordmgmtprojcorejava.model.Item;
import com.training.ordmgmtprojcorejava.model.Order;

public class OrderDataStore {
	
	private static Map<Long,Order> orderIdOrderObjMap = new HashMap<Long,Order>();
	
	private static List<Item> cartItems = new ArrayList<Item>() ;

	public static Map<Long, Order> getOrderIdOrderObjMap() {
		return orderIdOrderObjMap;
	}

	public static void setOrderIdOrderObjMap(Map<Long, Order> orderIdOrderObjMap) {
		OrderDataStore.orderIdOrderObjMap = orderIdOrderObjMap;
	}

	public static List<Item> getCartItems() {
		return cartItems;
	}

	public static void setCartItems(List<Item> cartItems) {
		OrderDataStore.cartItems = cartItems;
	} 
	
	
}
