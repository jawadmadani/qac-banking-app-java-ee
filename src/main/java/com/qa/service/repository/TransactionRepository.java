package com.qa.service.repository;

public interface TransactionRepository {
	
	String getAllTransactionStatement(long ACC_ID);
	
	String getBalance(long ACC_ID);

}
