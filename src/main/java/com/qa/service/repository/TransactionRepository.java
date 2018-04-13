package com.qa.service.repository;

public interface TransactionRepository {
	
	String getAllTransactions(long accountid);
	
	String getAllTransactionStatement(long accountid);
	
	String createTransaction(String transaction);
	
	String updateTransaction(Long id, String transactionToUpdate);
	
	String deleteTransaction(Long id);
	
}
