package com.qa.service.business;

public interface TransactionService {
	
	String getAllTransactions();
	
	String addTransaction(String transaction);
	
	String updateTransaction(Long id, String transaction);
	
	String deleteTransaction(Long id);

}
