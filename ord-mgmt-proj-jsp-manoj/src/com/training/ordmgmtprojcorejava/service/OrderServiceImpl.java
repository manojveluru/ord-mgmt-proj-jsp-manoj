package com.training.ordmgmtprojcorejava.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpSession;

import com.training.ordmgmtprojcorejava.dao.OrderDao;
import com.training.ordmgmtprojcorejava.factory.DaoObjectFactory;
import com.training.ordmgmtprojcorejava.model.Item;
import com.training.ordmgmtprojcorejava.model.Order;

public class OrderServiceImpl implements OrderService {

	@Override
	public List<Item> getCartItems() {
		OrderDao orderDao = DaoObjectFactory.getOrderDao();
		List<Item> cartItems = orderDao.getCartItems();
		return cartItems;
	}
	
	@Override
	public List<Item> getItemsSelected(List<Item> itemList) {
		Scanner scanner = new Scanner(System.in);
		List<Item> cartItems = new ArrayList<>();

		while (true) {

			System.out.println("Enter the Item Id which you want to purchase");
			int purchaseItem = scanner.nextInt();
			System.out.println("Enter the Quantity of the Item which you want to purchase");
			int quantity = scanner.nextInt();
			for (Item item : itemList) {
				if (item.getItemId() == purchaseItem) {
					item.setQuantity(quantity);
					cartItems.add(item);
					System.out.println();
					break;
				}
			}
			System.out.println("Would you like to Add more Items Y/N");
			String decision = scanner.next();

			if (decision.equalsIgnoreCase("N"))
				break;

		}

		return cartItems;
	}

	
	
	@Override
	public Order calculatePrice(List<Item> cartItems) {

		Order order = new Order();
		float itemTotalPrice;
		float itemPrice;
		double ordTotalPrice = 0;
		List<Item> finalItems = new ArrayList<Item>();
		finalItems.addAll(cartItems);
		for (Item itemobj : finalItems) {

			if (itemobj.isOnSale()) {

				float disc = (itemobj.getItemPrice() * itemobj.getDiscount()) / 100;

				itemPrice = itemobj.getItemPrice() - disc;
				itemTotalPrice = itemPrice * itemobj.getQuantity();
			} else {
				itemPrice = itemobj.getItemPrice();
				itemTotalPrice = itemPrice * itemobj.getQuantity();
			}
			itemobj.setItemPrice(itemPrice);
			itemobj.setItemTotalPrice(itemTotalPrice);
			ordTotalPrice = ordTotalPrice + itemTotalPrice;
			

		}
		order.setTotalPrice(ordTotalPrice);
		order.setItems(finalItems);

		return order;
	}

	@Override
	public long insertOrder(Order order) {
		OrderDao orderDao = DaoObjectFactory.getOrderDao();
		long orderId = orderDao.getOrderSequenceId();
		order.setOrderId(orderId);
		orderDao.insertOrderDetails(order, order.getCustomerId());
		orderDao.insertOrderItem(order);
		return orderId;
	}

	@Override
	public boolean clearCartItems() {
		OrderDao orderDao = DaoObjectFactory.getOrderDao();
		boolean result = orderDao.clearCartItems();
		return result;
	}

	@Override
	public Order getOrderByOrderId(Long orderId) {
		OrderDao orderDao = DaoObjectFactory.getOrderDao();
		Order order = orderDao.getOrderByOrderId(orderId);
		return order;
	}

	@Override
	public boolean deleteOrder(Long orderId) {
		OrderDao orderDao = DaoObjectFactory.getOrderDao();
		boolean result = orderDao.deleteOrder(orderId);
		return result;
	}

	@Override
	public boolean addToCart(Item item, HttpSession httpSession) {
		
		if (httpSession.getAttribute("cartItems") != null) {
			List<Item> alItems = (List<Item>) httpSession.getAttribute("cartItems");
			alItems.add(item);
		} else {
			List<Item> alItems = new ArrayList();
			alItems.add(item);
			httpSession.setAttribute("cartItems", alItems);

		}
		return true;
	}

	@Override
	public List<Item> getCartItems(HttpSession httpSession) {
		List<Item> cartItems = (List<Item>) httpSession.getAttribute("cartItems");
		return cartItems;
	}

}
