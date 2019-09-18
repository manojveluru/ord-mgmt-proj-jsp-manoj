package com.training.ordmgmtprojcorejava.factory;

import com.training.ordmgmtprojcorejava.service.CustomerService;
import com.training.ordmgmtprojcorejava.service.CustomerServiceImpl;
import com.training.ordmgmtprojcorejava.service.ItemService;
import com.training.ordmgmtprojcorejava.service.ItemServiceImpl;
import com.training.ordmgmtprojcorejava.service.OrderService;
import com.training.ordmgmtprojcorejava.service.OrderServiceImpl;

/**
 * This class decides on what implementation object to be returned.
 * Returning an interface reference to maintain loosely coupled applications.
 * 
 * As we are returning interfaces, in the future we can replace ItemServiceImpl and OrderServiceImpl 
 * with other logic related implementation classes without affecting the client code.
 * 
 * @author kmk
 *
 */
public class ServiceObjectFactory {
	
	public static ItemService getItemService() {
		ItemService itemService = new ItemServiceImpl();
		return itemService;
	}
	
	public static OrderService getOrderService() {
		OrderService orderService = new OrderServiceImpl();
		return orderService;
	}
	
	public static CustomerService getCustomerService() {
		CustomerService customerService = new CustomerServiceImpl();
		return customerService;
	}

}
