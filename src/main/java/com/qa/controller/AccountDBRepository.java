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
		Query query = manager.createQuery("insert into Account (ACCOUNT_NUMBER, CUS_ID) values (" + ACCOUNT_NUMBER + "," +  CUS_ID + ")");
//		Account anAccount = util.getObjectForJSON(accout, Account.class); 
//		manager.persist(anAccount); //add account class using persist database
//		return "{\"message\": \"account has been sucessfully added\"}"; 
		return "";
	}
//
//	@Override
//	@Transactional(REQUIRED)
//	public String updateAccount(Long id, String accountToUpdate) { //update, take in number of object we want to update (the id), the actual json object that we wanna update with
//		Account updatedAccount = util.getObjectForJSON(accountToUpdate, Account.class); //get json string and put it into object
//		Account accountFromDB = findAccount(id); //find object in database based on id, so we get the object that we actually wanna update
//		if (accountToUpdate != null) { //if not null, update account
//			accountFromDB = updatedAccount;
//			manager.merge(accountFromDB);
//		}
//		return "{\"message\": \"account sucessfully updated\"}";
//	}
//
//	@Override
//	@Transactional(REQUIRED) 
//	public String deleteAccount(Long id) { //takes the id of object that we wanna delete
//		Account accountInDB = findAccount(id); 
//		if (accountInDB != null) {
//			manager.remove(accountInDB);
//			return "{\"message\": \"account sucessfully deleted\"}";
//		}
//		return "{\"message\": \"account not found\"}";
//	}

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
