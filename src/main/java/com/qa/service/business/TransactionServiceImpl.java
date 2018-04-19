package com.qa.service.business;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.qa.service.repository.TransactionRepository;

public class TransactionServiceImpl implements TransactionService{
	
	private static final Logger LOGGER = Logger.getLogger(AccountService.class);
	
	@Inject
	private TransactionRepository repo;
	
	@Override
	public String getAllTransactionStatement(long ACC_ID) {
		LOGGER.info("Transaction ServiceImpl - Get request - getAllTransactionStatement");
		return repo.getAllTransactionStatement(ACC_ID);
	}
	
}
