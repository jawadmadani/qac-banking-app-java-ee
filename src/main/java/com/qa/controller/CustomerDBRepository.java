package com.qa.controller;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.qa.domain.Customer;
import com.qa.service.repository.CustomerRepository;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class CustomerDBRepository implements CustomerRepository{
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	@Inject
	private JSONUtil util;
	
	@Override
	public String getAllCustomers() {
		Query query = manager.createQuery("Select a FROM Customer a");
		Collection<Customer> customers = (Collection<Customer>) query.getResultList();
		return util.getJSONForObject(customers);
	}

	@Override
	@Transactional(REQUIRED)
	public String createCustomer(String customer) {
		Customer aCustomer = util.getObjectForJSON(customer, Customer.class);
		manager.persist(aCustomer);
		return "{\"message\": \"Customer has been successfully added\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String updateCustomer(Long id, String customerToUpdate) {
		Customer updatedCustomer = util.getObjectForJSON(customerToUpdate, Customer.class);
		Customer customerFromDB = findCustomer(id);
		if (customerToUpdate != null) {
			customerFromDB = updatedCustomer;
			manager.merge(customerFromDB);
		}
		return "{\"Message\": \"Customer successfully updated\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteCustomer(Long id) {
		Customer customerInDB = findCustomer(id);
		if (customerInDB != null) {
			manager.remove(customerInDB);
			return "{\"Message\": \"Customer successfully removed\"}";
		}
		return "{\"Message\": \"Customer cannot be found\"}";
	}
	
	private Customer findCustomer(Long id) {
		return manager.find(Customer.class, id);
	}
	
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}
	
	public void setUtil(JSONUtil util) {
		this.util = util;
	}

}
