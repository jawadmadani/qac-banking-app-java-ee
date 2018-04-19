package com.qa.service.business;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.qa.service.repository.AccountRepository;

public class AccountServiceImpl implements AccountService {

	private static final Logger LOGGER = Logger.getLogger(AccountService.class);

	@Inject
	private AccountRepository repo;
	
	@Override
	public String getAllAccounts(Long CUS_ID) {
		LOGGER.info("At AccountServiceImpl - GET REquest - getAllAccounts ");
		LOGGER.info("Customer id: " + CUS_ID);
		return repo.getAllAccounts(CUS_ID);
	}

	@Override
	public String createAccount(String ACCOUNT_NUMBER, Long CUS_ID) {
		LOGGER.info("At  Account serviceImpl - post request - add account");
		LOGGER.info(ACCOUNT_NUMBER + "---" + CUS_ID);
		return repo.createAccount(ACCOUNT_NUMBER, CUS_ID);
	}
  
	@Override
	public String deleteAccount(Long id) {
		LOGGER.info("At Account serviceImpl - delete request - deleteAccount");
		LOGGER.info("account id: " + id);
		return repo.deleteAccount(id);
	}

	public void setRepo(AccountRepository repo) {
		this.repo = repo;
	}
}
