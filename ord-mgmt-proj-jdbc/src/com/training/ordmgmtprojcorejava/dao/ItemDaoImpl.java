package com.training.ordmgmtprojcorejava.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.training.ordmgmtprojcorejava.model.Item;
import com.training.ordmgmtprojcorejava.model.Order;

import oracle.jdbc.driver.OracleDriver;

public class ItemDaoImpl implements ItemDao {
	
	public static final String GET_ITEMS_SQL = "SELECT ITEM_ID, ITEM_NAME, ITEM_DESC, ITEM_PRICE, ITEM_IS_ON_SALE, ITEM_DISCOUNT FROM ITEM WHERE LOWER(ITEM_NAME) LIKE ?";

	@Override
	public List<Item> searchItemsByKeyword(String keyword) {

		List<Item> searchResults = new ArrayList<Item>();
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pst = conn.prepareStatement(GET_ITEMS_SQL);
			
			
			pst.setString(1, "%" + keyword.toLowerCase() + "%");
			rs = pst.executeQuery();

			while (rs.next()) {
				
				
				int id = rs.getInt("ITEM_ID");
				String name = rs.getString("ITEM_NAME");
				String description = rs.getString("ITEM_DESC");
				int price = rs.getInt("ITEM_PRICE");
				boolean onSale = "Y".equalsIgnoreCase(rs.getString("ITEM_IS_ON_SALE")) ? true : false;
				int discount = rs.getInt("ITEM_DISCOUNT");
				
				Item product = new Item();
				
				product.setItemId(id);
				product.setItemName(name);
				product.setItemDescription(description);
				product.setItemPrice(price);
				product.setOnSale(onSale);
				product.setDiscount(discount);

				searchResults.add(product);


			}

			conn.commit();

		} catch (SQLException sqlEx) {
			System.out.println("Problem while executing sql");

		} catch (Exception ex) {
			System.out.println("Unknown exception.");

		} finally {
			try {
				rs.close();
				pst.close();
				conn.close();

			} catch (SQLException sqlExce) {
				System.out.println("Problem while closing the connection.");
			}

		}

		return searchResults;
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
