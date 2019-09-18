package com.training.ordmgmtprojcorejava.service;

import java.util.List;

import com.training.ordmgmtprojcorejava.model.Item;
import com.training.ordmgmtprojcorejava.model.Order;

public interface ItemService {

	public abstract List<Item> searchItemsByKeyword(String keyword);

	public abstract void displayItems(List<Item> itemList);

}
