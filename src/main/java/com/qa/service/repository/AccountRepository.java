package com.qa.service.repository;

public interface AccountRepository {

	String getAllAccounts(Long CUS_ID);

	void createAccount(String ACCOUNT_NUMBER, Long CUS_ID);
//
//	String updateAccount(Long id, String accountToUpdate);
//
//	String deleteAccount(Long id);

}