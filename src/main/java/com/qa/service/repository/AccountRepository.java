package com.qa.service.repository;

public interface AccountRepository {

	String getAllAccounts(Long CUS_ID);

	String createAccount(String ACCOUNT_NUMBER, Long CUS_ID);

	String deleteAccount(Long id);

}