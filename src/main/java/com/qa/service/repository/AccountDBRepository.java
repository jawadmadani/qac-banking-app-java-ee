package com.qa.service.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
@Default //you can only have one default on one class, having it on AccountMapRepository would return error
public class AccountDBRepository implements AccountRepository { //AccountRepository is an interface

	@PersistenceContext(unitName = "primary") //linked to persistence.xml
	private EntityManager manager;

	@Inject //beans container, handle life cycle of this object
	private JSONUtil util;

	@Override
	public String getAllAccounts() {
		Query query = manager.createQuery("Select a FROM Account a"); //create new query
		Collection<Account> accounts = (Collection<Account>) query.getResultList(); //execute query object
		return util.getJSONForObject(accounts); //transfer and change it into json object
	}

	@Override
	@Transactional(REQUIRED)
	public String createAccount(String accout) { //takes in json obect, the thing that's coming in
		Account anAccount = util.getObjectForJSON(accout, Account.class); //change string object into account class
		manager.persist(anAccount); //add account class using persist database
		return "{\"message\": \"account has been sucessfully added\"}"; 
	}

	@Override
	@Transactional(REQUIRED)
	public String updateAccount(Long id, String accountToUpdate) { //update, take in number of object we want to update (the id), the actual json object that we wanna update with
		Account updatedAccount = util.getObjectForJSON(accountToUpdate, Account.class); //get json string and put it into object
		Account accountFromDB = findAccount(id); //find object in database based on id, so we get the object that we actually wanna update
		if (accountToUpdate != null) { //if not null, update account
			accountFromDB = updatedAccount;
			manager.merge(accountFromDB);
		}
		return "{\"message\": \"account sucessfully updated\"}";
	}

	@Override
	@Transactional(REQUIRED) 
	public String deleteAccount(Long id) { //takes the id of object that we wanna delete
		Account accountInDB = findAccount(id); 
		if (accountInDB != null) {
			manager.remove(accountInDB);
			return "{\"message\": \"account sucessfully deleted\"}";
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
