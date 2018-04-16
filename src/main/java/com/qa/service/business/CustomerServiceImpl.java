package com.qa.service.business;

import javax.inject.Inject;

import com.qa.service.repository.CustomerRepository;

public class CustomerServiceImpl implements CustomerService{
	
	@Inject
	private CustomerRepository repo;

	@Override
	public String getCustomer(String USERNAME, String PASSWORD) {
		return repo.getCustomer(USERNAME, PASSWORD);
	}

	@Override
	public String addCustomer(String FIRST_NAME, String SECOND_NAME, String USERNAME, String PASSWORD) {
		return repo.createCustomer(FIRST_NAME, SECOND_NAME, USERNAME, PASSWORD);
	}
//
//	@Override
//	public String updateCustomer(Long id, String customer) {
//		return repo.updateCustomer(id, customer);
//	}
//
//	@Override
//	public String deleteCustomer(Long id) {
//		return repo.deleteCustomer(id);
//	}
//	
//	public void setRepo(CustomerRepository repo) {
//		this.repo = repo;
//	}

}
