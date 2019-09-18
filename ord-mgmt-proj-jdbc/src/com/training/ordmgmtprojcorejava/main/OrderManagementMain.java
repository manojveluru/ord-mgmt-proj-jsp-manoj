package com.training.ordmgmtprojcorejava.main;

import java.util.List;
import java.util.Scanner;

import com.training.ordmgmtprojcorejava.factory.ServiceObjectFactory;
import com.training.ordmgmtprojcorejava.model.Item;
import com.training.ordmgmtprojcorejava.model.Order;
import com.training.ordmgmtprojcorejava.service.CustomerService;
import com.training.ordmgmtprojcorejava.service.ItemService;
import com.training.ordmgmtprojcorejava.service.OrderService;

public class OrderManagementMain {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("*******************************************");
			System.out.println("       Welcome to Order Management         ");
			System.out.println("*******************************************");
			System.out.println("Select One of the following options ");
			System.out.println("1. PlaceOrder");
			System.out.println("2. Search Order");
			System.out.println("3. Delete Order");
			System.out.println("4. Exit");
			int selection = scanner.nextInt();
			
			ItemService itemService = ServiceObjectFactory.getItemService();
			OrderService orderService = ServiceObjectFactory.getOrderService();
			CustomerService customerService = ServiceObjectFactory.getCustomerService();
			
			switch (selection) {
				case 1:{
	
					while (true) {
						
						try {
							System.out.println("Enter the keyword to search the items");
							String keyword = scanner.next();
							//Search items for the given keyword
							List<Item> itemList = itemService.searchItemsByKeyword(keyword);
							if (itemList == null || itemList.size() <= 0) {
								System.out.println("No Results Found");
								continue;
							}
							//Display the searched items
							itemService.displayItems(itemList);
							//Ask the user to select the items from searched items
							List<Item> selectedItems = orderService.getItemsSelected(itemList);	
							//Update the cart with selected items
							boolean addToCartStatus = orderService.addToCart(selectedItems); //use the addToCartStatus as needed
		
							System.out.println("Would you like to Proceed to checkout Y/N");
							String decision = scanner.next();
							if ("Y".equalsIgnoreCase(decision)) {
								System.out.println("Enter username");
								String username = scanner.next();
								System.out.println("Enter password");
								String password = scanner.next();
								long customerId = customerService.validateUser(username, password);
								if (customerId > 0) {
									//Get cartItems
									List<Item> cartItems = orderService.getCartItems();
									//Calculate the price for cartItems and get the Order
									Order order = orderService.calculatePrice(cartItems);
									order.setCustomerId(customerId);	
									//Insert Order
									long orderId = orderService.insertOrder(order); //use the insertOrderStatus as needed
									
									if(orderId > 0) {
										System.out.println(username+", Your order has been placed.");
										System.out.println("Total price of your Order is " + order.getTotalPrice());
										System.out.println("Order ID =" + orderId);
									}else {
										System.out.println("Sorry, we are unable to process the order.");
									}
									//Clear cartItems
									boolean clearCartItemsStatus = orderService.clearCartItems(); //use the clearCartItemsStatus as needed
								}else {
									System.out.println("Invalid User. Please start over the search.");
									//Clear cartItems
									boolean clearCartItemsStatus = orderService.clearCartItems(); //use the clearCartItemsStatus as needed
								}
								break;
							}
						
					    }catch(Exception ex) {
					    	System.out.println("Problem while processing the order.");
					    }
	
					}
					break;
				}
	
				case 2:{
					
					try {
						System.out.println("Enter the Order ID You Would like to Search");
						long orderId = scanner.nextLong();
						//Get the Order by oraderId
						Order order = orderService.getOrderByOrderId(orderId);
						
						if (order != null && order.getItems() != null) {
							System.out.println("Total Price = " + order.getTotalPrice());
							itemService.displayItems(order.getItems());
		
						} else {
							System.out.println("No Order Id Found");
						}
					 }catch(Exception ex) {
					    	System.out.println("Problem while Searching the order.");
					 }
	
					break;
				}
				case 3:{
					try {
						System.out.println("Enter the Order ID You Would like to Delete");
						long deleteOrderId = scanner.nextLong();
						//Get the Order by oraderId
						Order deleteOrder = orderService.getOrderByOrderId(deleteOrderId);				
						
						if (deleteOrder != null && deleteOrder.getItems() != null) {
							System.out.println("Total Price = " + deleteOrder.getTotalPrice());
							itemService.displayItems(deleteOrder.getItems());
							boolean deleteOrderStatus = orderService.deleteOrder(deleteOrderId); //use the deleteOrderStatus as needed
							System.out.println("Order Id has been Removed");
		
						} else {
							System.out.println("No Order Id Found");
						}
					 }catch(Exception ex) {
					    	System.out.println("Problem while Deleting the order.");
					 }
	
					break;
				}
				case 4:{
					System.exit(1);
				}
			}//end switch

		}//end main while

	}//end main

}
