package com.training.ordmgmtprojcorejava.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.training.ordmgmtprojcorejava.dao.CustomerDao;
import com.training.ordmgmtprojcorejava.dao.OrderDao;
import com.training.ordmgmtprojcorejava.factory.DaoObjectFactory;
import com.training.ordmgmtprojcorejava.model.Item;

import oracle.jdbc.driver.OracleDriver;

public class CustomerServiceImpl implements CustomerService {

	@Override
	public long validateUser(String username, String password) {
		CustomerDao customerDao = DaoObjectFactory.getCustomerDao();
		long customerId = customerDao.validateUser(username, password);
		return customerId;
	}

}
