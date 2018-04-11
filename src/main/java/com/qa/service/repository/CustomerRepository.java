package com.qa.service.repository;

public interface CustomerRepository {
	
	String getAllCustomers();
	
	String createCustomer(String customer);
	
	String updateCustomer(Long id, String customerToUpdate);
	
	String deleteCustomer(Long id);
	
}
