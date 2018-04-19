package com.qa.service.business;

public interface TransactionService {
	
	String getAllTransactions(long ACC_ID);
	
	String getAllTransactionStatement(long ACC_ID);

}
