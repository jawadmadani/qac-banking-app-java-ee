package com.qa.service.business;

import javax.inject.Inject;

import com.qa.service.repository.CustomerRepository;

public class CustomerServiceImpl implements CustomerService{
	
	@Inject
	private CustomerRepository repo;

	@Override
	public String getAllCustomers() {
		return repo.getAllCustomers();
	}

	@Override
	public String addCustomer(String customer) {
		return repo.createCustomer(customer);
	}

	@Override
	public String updateCustomer(Long id, String customer) {
		return repo.updateCustomer(id, customer);
	}

	@Override
	public String deleteCustomer(Long id) {
		return repo.deleteCustomer(id);
	}
	
	public void setRepo(CustomerRepository repo) {
		this.repo = repo;
	}

}
