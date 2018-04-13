package com.qa.service.business;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.qa.service.repository.TransactionRepository;

public class TransactionServiceImpl implements TransactionService{
	
	private static final Logger LOGGER = Logger.getLogger(AccountService.class);
	
	@Inject
	private TransactionRepository repo;

	@Override
	public String getAllTransactions(long accountid) {
		LOGGER.info("testing..." + repo.getAllTransactions(accountid));
		return repo.getAllTransactions(accountid);
	}

	@Override
	public String addTransaction(String transaction) {
		return repo.createTransaction(transaction);
	}

	@Override
	public String updateTransaction(Long id, String transaction) {
		return repo.updateTransaction(id, transaction);
	}

	@Override
	public String deleteTransaction(Long id) {
		return repo.deleteTransaction(id);
	}
	
	public void setRepo(TransactionRepository repo) {
		this.repo = repo;
	}

	@Override
	public String getAllTransactionStatement(long accountid) {
		// TODO Auto-generated method stub
		return null;
	}

}
