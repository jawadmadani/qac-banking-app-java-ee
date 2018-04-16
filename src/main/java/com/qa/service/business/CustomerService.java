package com.qa.service.business;

public interface CustomerService {
	
	String getCustomer(String USERNAME, String PASSWORD);
	
	String addCustomer(String FIRST_NAME, String SECOND_NAME, String USERNAME, String PASSWORD);
//	
//	String updateCustomer(Long id, String customer);
//	
//	String deleteCustomer(Long id);

}
