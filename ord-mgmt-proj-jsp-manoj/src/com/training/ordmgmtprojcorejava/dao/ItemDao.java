package com.training.ordmgmtprojcorejava.dao;

import java.util.List;

import com.training.ordmgmtprojcorejava.model.Item;
import com.training.ordmgmtprojcorejava.model.Order;

public interface ItemDao {

	public abstract List<Item> searchItemsByKeyword(String keyword);
	public abstract Item getItemByItemId(long itemId);
	
}
