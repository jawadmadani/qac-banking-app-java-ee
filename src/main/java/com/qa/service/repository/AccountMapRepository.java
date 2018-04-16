//package com.qa.service.repository;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.enterprise.context.ApplicationScoped;
//import javax.enterprise.inject.Alternative;
//import javax.inject.Inject;
//
//import com.qa.domain.Account;
//import com.qa.util.JSONUtil;
//
//@Alternative //alternative to default in AccountDBRepository, think back to the example of switching between hashmap and db example
//@ApplicationScoped //bean is gonna be created and destroyed when application is stopped, hence the scope
//public class AccountMapRepository implements AccountRepository { //alternative implementation to AccountDBRepository.java, this is polymorphism right here, implement same class but different
//
//	private final Long INITIAL_COUNT = 1L;
//	private Map<Long, Account> accountMap;
//	private Long ID;
//
//	@Inject
//	private JSONUtil util;
//
///*	public AccountMapRepository() {
//		this.accountMap = new HashMap<Long, Account>();
//		ID = INITIAL_COUNT;
//		initAccountMap();
//	}*/
//
//	@Override
//	public String getAllAccounts() {
//		return util.getJSONForObject(accountMap.values());
//	}
//
//	@Override
//	public String createAccount(String account) {
//		ID++;
//		Account newAccount = util.getObjectForJSON(account, Account.class);
//		accountMap.put(ID, newAccount);
//		return account;
//	}
//
//	@Override
//	public String updateAccount(Long id, String accountToUpdate) {
//		Account newAccount = util.getObjectForJSON(accountToUpdate, Account.class);
//		accountMap.put(id, newAccount);
//		return accountToUpdate;
//	}
//
//	@Override
//	public String deleteAccount(Long id) {
//		accountMap.remove(id);
//		return "{\"message\": \"accout sucessfully removed\"}";
//	}
//
///*	private void initAccountMap() {
//		Account account = new Account("1234");
//		accountMap.put(1L, account);
//	}
//*/
//}
