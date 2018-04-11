package com.qa.service.business;

public interface CustomerService {
	
	String getAllCustomers();
	
	String addCustomer(String customer);
	
	String updateCustomer(Long id, String customer);
	
	String deleteCustomer(Long id);

}
