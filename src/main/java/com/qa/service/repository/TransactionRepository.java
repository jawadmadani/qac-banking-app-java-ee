package com.qa.service.repository;

public interface TransactionRepository {
	
	String getAllTransactions(long ACC_ID);
	
	String getAllTransactionStatement(long ACC_ID);

}
