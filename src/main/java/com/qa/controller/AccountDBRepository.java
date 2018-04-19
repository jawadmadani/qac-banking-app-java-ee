package com.qa.controller;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;
import java.util.Iterator;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;

import com.qa.domain.Account;
import com.qa.domain.Customer;
import com.qa.service.repository.AccountRepository;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class AccountDBRepository implements AccountRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil util;

	private static final Logger LOGGER = Logger.getLogger(AccountDBRepository.class);

	@Override
	public String getAllAccounts(Long CUS_ID) {
		Query query = manager.createQuery("Select a FROM Account a where CUS_ID = '"+ CUS_ID +"'");
		Collection<Account> accounts = (Collection<Account>) query.getResultList();
		LOGGER.info("At AccountDBRepository - GET Request - getAllAccounts");
		LOGGER.info("Customer id: " + CUS_ID);
		LOGGER.info(accounts);
		if(accounts.size() == 0){
			return "{\"result\":\"fail\"}";
		} else {
			String json = "";
			Iterator<Account> it = accounts.iterator();
			while(it.hasNext()){
				Account account = it.next();
				LOGGER.info(account);
				json = json + "{\"ACC_ID\":\"" + account.getId() + "\",\"accountNumber\":\"" + account.getAccountNumber() +"\"},";
			}
			json = json.substring(0, json.length() - 1);
			json = "[" + json + "]";
			LOGGER.info(json);
			return json;
		}
	}

	@Override
	@Transactional(REQUIRED)
	public String createAccount(String ACCOUNT_NUMBER, Long CUS_ID) { 
		LOGGER.info("At Account DB repo - post request - createAccount");
		LOGGER.info(ACCOUNT_NUMBER + "---" + CUS_ID);
		
		Account newAccount = new Account();
		newAccount.setAccountNumber(ACCOUNT_NUMBER);
		Customer cusID = new Customer();
		cusID.setId(CUS_ID);
		newAccount.setCustomer(cusID);
		manager.persist(newAccount);
		
		return "{\"result\":\"run\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteAccount(Long id) {
		LOGGER.info("At Account Db Repository - DELETE request - deleteAccount");
		LOGGER.info("Account id: " + id);
		Account accountInDB = findAccount(id);
		LOGGER.info("account: " + accountInDB);
		if (accountInDB != null) {
			manager.remove(accountInDB);
			return "{\"message\": \"account successfully deleted\"}";
		}
		return "{\"message\": \"account not found\"}";
	}

	private Account findAccount(Long id) {
		return manager.find(Account.class, id);
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}
	
}
