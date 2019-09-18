package com.training.ordmgmtprojcorejava.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.driver.OracleDriver;

public class CustomerDaoImpl implements CustomerDao {
	public static final String GET_CustomerID_SQL = "SELECT CUST_ID, USER_NAME FROM CUSTOMER WHERE USER_NAME = ? AND PASSWORD = ?";

	@Override
	public long validateUser(String username, String password) {
		long customerId = 0;

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pst = conn.prepareStatement(GET_CustomerID_SQL);

			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();

			while (rs.next()) {

				customerId = rs.getInt("CUST_ID");
				String name = rs.getString("USER_NAME");

				System.out.println("Customer ID :"+customerId);
				System.out.println("Customer Username: "+name);
			}


		} catch (SQLException sqlEx) {
			System.out.println("Problem while executing sql");

		} catch (Exception ex) {
			System.out.println("Unknown exception.");

		} finally {
			try {
				pst.close();
				conn.close();

			} catch (SQLException sqlExce) {
				System.out.println("Problem while closing the connection.");
			}

		}

		return customerId;
	}

	private static Connection getConnection() throws Exception {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String username = "SYSTEM";
		String password = "SYSADMIN";
		DriverManager.registerDriver(new OracleDriver());
		Connection conn = DriverManager.getConnection(url, username, password);
		conn.setAutoCommit(false);

		return conn;
	}

}
