package com.training.ordmgmtprojcorejava.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.training.ordmgmtprojcorejava.datastore.OrderDataStore;
import com.training.ordmgmtprojcorejava.model.Item;
import com.training.ordmgmtprojcorejava.model.Order;

import oracle.jdbc.driver.OracleDriver;

public class OrderDaoImpl implements OrderDao {
	
	public static final String GET_ORDER_SEQ_ID = "SELECT ORDER_DETAILS_SEQ.nextval ORDER_ID FROM DUAL";
	public static final String INSERT_ORDER_SQL = "INSERT INTO ORDER_DETAILS(ORDER_ID, CUST_ID, TOTAL_PRICE) VALUES(? , ?, ?)";
	public static final String INSERT_ORDER_ITEM = "INSERT INTO ORDER_ITEM(ORDER_ID,ITEM_ID,ITEM_PRICE,QUANTITY,TOTAL_PRICE) VALUES (?,?,?,?,?)";
	public static final String FIND_ORDER_BY_ITEM = "SELECT OD.ORDER_ID,OD.TOTAL_PRICE,OD.CUST_ID,OI.ITEM_ID,OI.ITEM_PRICE,OI.QUANTITY,OI.TOTAL_PRICE,I.ITEM_NAME,I.ITEM_DESC FROM ORDER_DETAILS OD JOIN ORDER_ITEM OI ON  OD.ORDER_ID=OI.ORDER_ID JOIN ITEM I ON OI.ITEM_ID = I.ITEM_ID WHERE OD.ORDER_ID=?";
	
	public static final String DELETE_ORDER_ITEM_BY_ITEM = "DELETE ORDER_ITEM WHERE ORDER_ID = ?";
	public static final String DELETE_ORDER_DETAILS_BY_ITEM = "DELETE ORDER_DETAILS WHERE ORDER_ID = ?";
	
	@Override
	public List<Item> getCartItems() {
		List<Item> cartItems = OrderDataStore.getCartItems();
		return cartItems;
	}

	

	@Override
	public void insertOrderDetails(Order order, long customerId) {
		Connection conn = null;
		PreparedStatement pst = null;

		try {
			conn = getConnection();
			pst = conn.prepareStatement(INSERT_ORDER_SQL);
			long orderId = order.getOrderId();

			double tprice = order.getTotalPrice();

			pst.setLong(1, orderId);
			pst.setLong(2, customerId);
			pst.setDouble(3, tprice);

			pst.executeUpdate();

			conn.commit();

		} catch (SQLException sqlEx) {
			System.out.println("Problem while executing sql 1");

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
	}

	@Override
	public void insertOrderItem(Order order) {

		Connection conn = null;
		PreparedStatement pst = null;

		try {
			conn = getConnection();
			pst = conn.prepareStatement(INSERT_ORDER_ITEM);
			long orderId = order.getOrderId();
			// double tprice = order.getTotalPrice();

			for (Item itm : order.getItems()) {

				int item_id = itm.getItemId();
				float item_price = itm.getItemPrice();
				int item_quantity = itm.getQuantity();
				double itemTotalPrice = itm.getItemTotalPrice();

				pst.setLong(1, orderId);
				pst.setInt(2, item_id);
				pst.setFloat(3, item_price);
				pst.setInt(4, item_quantity);
				pst.setDouble(5, itemTotalPrice);
				pst.executeUpdate();

			}

			conn.commit();

		} catch (SQLException sqlEx) {
			System.out.println("Problem while executing sql 2");

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

	}

	@Override
	public long getOrderSequenceId() {

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		long orderId = 0;
		try {
			conn = getConnection();
			st = conn.createStatement();

			rs = st.executeQuery(GET_ORDER_SEQ_ID);

			if (rs.next()) {
				orderId = rs.getInt("ORDER_ID");

			}

		} catch (SQLException sqlEx) {
			System.out.println("Problem while executing sql 3");

		} catch (Exception ex) {
			System.out.println("Unknown exception.");

		} finally {
			try {
				rs.close();
				st.close();
				conn.close();

			} catch (SQLException sqlExce) {
				System.out.println("Problem while closing the connection.");
			}

		}

		return orderId;
	}

	@Override
	public boolean clearCartItems() {
		List<Item> cartItems = OrderDataStore.getCartItems();
		cartItems.clear();
		return true;
	}

	@Override
	public Order getOrderByOrderId(Long orderId) {
		Order order = new Order();
		List<Item> itemList = new ArrayList<Item>();

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pst = conn.prepareStatement(FIND_ORDER_BY_ITEM);

			pst.setLong(1, orderId);

			rs = pst.executeQuery();

			while (rs.next()) {

				Item item = new Item();

				double totalPrice = rs.getDouble("TOTAL_PRICE");
				int custid = rs.getInt("CUST_ID");
				long oId = rs.getLong("ORDER_ID");

				int itemPrice = rs.getInt("ITEM_PRICE");
				int quantity = rs.getInt("QUANTITY");
				float itemTotalPrice = rs.getFloat("TOTAL_PRICE");
				int itemId = rs.getInt("ITEM_ID");
				String itemName = rs.getString("ITEM_NAME");
				String itemdes = rs.getString("ITEM_DESC");
				

				order.setOrderId(orderId);
				order.setTotalPrice(totalPrice);
				order.setCustomerId(custid);

				item.setItemId(itemId);
				item.setItemPrice(itemPrice);
				item.setQuantity(quantity);
				item.setItemTotalPrice(itemTotalPrice);
				item.setItemName(itemName);
				item.setItemDescription(itemdes);

				itemList.add(item);
			}
			
			order.setItems(itemList);

		} catch (SQLException sqlEx) {
			System.out.println("Problem while executing sql");
			sqlEx.printStackTrace();

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
		return order;
	}

	@Override
	public boolean deleteOrder(Long orderId) {
		Connection conn = null;
		PreparedStatement pst = null;

		try {
			conn = getConnection();
			pst = conn.prepareStatement(DELETE_ORDER_ITEM_BY_ITEM);
			pst.setLong(1, orderId);
			pst.executeUpdate();

			pst = conn.prepareStatement(DELETE_ORDER_DETAILS_BY_ITEM);
			pst.setLong(1, orderId);
			pst.executeUpdate();
			conn.commit();

		} catch (SQLException sqlEx) {
			System.out.println("Problem while executing sql");
			sqlEx.printStackTrace();

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

		return true;
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
