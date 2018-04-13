package com.qa.service.business;

public interface TransactionService {
	
	String getAllTransactions(long accountid);
	
	String getAllTransactionStatement(long accountid);
	
	String addTransaction(String transaction);
	
	String updateTransaction(Long id, String transaction);
	
	String deleteTransaction(Long id);

}
