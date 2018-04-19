package com.qa.service.business;

public interface AccountService {

	String getAllAccounts(Long CUS_ID);

	String createAccount(String ACCOUNT_NUMBER, Long CUS_ID);
//
//	String updateAccount(Long id, String account);

	String deleteAccount(Long id);

}