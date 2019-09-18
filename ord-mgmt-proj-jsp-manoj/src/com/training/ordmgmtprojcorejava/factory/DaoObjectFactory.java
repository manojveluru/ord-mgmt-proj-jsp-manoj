package com.training.ordmgmtprojcorejava.factory;

import com.training.ordmgmtprojcorejava.dao.CustomerDao;
import com.training.ordmgmtprojcorejava.dao.CustomerDaoImpl;
import com.training.ordmgmtprojcorejava.dao.ItemDao;
import com.training.ordmgmtprojcorejava.dao.ItemDaoImpl;
import com.training.ordmgmtprojcorejava.dao.OrderDao;
import com.training.ordmgmtprojcorejava.dao.OrderDaoImpl;

/**
 * This class decides on what implementation object to be returned.
 * Returning an interface reference to maintain loosely coupled applications.
 * 
 * As we are returning interfaces, in the future we can replace ItemDaoImpl and OrderDaoImpl 
 * with other database related implementation classes without affecting the client code.
 * 
 * @author kmk
 *
 */
public class DaoObjectFactory {
	
	public static ItemDao getItemDao() {
		ItemDao itemDao = new ItemDaoImpl();
		return itemDao;
	}
	
	public static OrderDao getOrderDao() {
		OrderDao orderDao = new OrderDaoImpl();
		return orderDao;
	}
	
	public static CustomerDao getCustomerDao() {
		CustomerDao orderDao = new CustomerDaoImpl();
		return orderDao;
	}

}
