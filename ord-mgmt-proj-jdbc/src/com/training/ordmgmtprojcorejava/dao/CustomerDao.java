package com.training.ordmgmtprojcorejava.dao;

public interface CustomerDao {
	public abstract long validateUser(String username, String password);
}
