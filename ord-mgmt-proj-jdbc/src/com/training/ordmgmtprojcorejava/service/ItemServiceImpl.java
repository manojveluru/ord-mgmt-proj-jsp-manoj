package com.training.ordmgmtprojcorejava.service;

import java.util.List;
import java.util.Scanner;

import com.training.ordmgmtprojcorejava.dao.ItemDao;
import com.training.ordmgmtprojcorejava.factory.DaoObjectFactory;
import com.training.ordmgmtprojcorejava.model.Item;
import com.training.ordmgmtprojcorejava.model.Order;

public class ItemServiceImpl implements ItemService {

	Scanner scanner = new Scanner(System.in);

	@Override
	public List<Item> searchItemsByKeyword(String keyword) {

		ItemDao itemDao = DaoObjectFactory.getItemDao();
		List<Item> searchResults = itemDao.searchItemsByKeyword(keyword);

		return searchResults;
	}

	@Override
	public void displayItems(List<Item> itemList) {

		for (Item item : itemList) {
			System.out.println(item);
		}

	}



}