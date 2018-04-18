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

import org.apache.log4j.Logger;

import com.qa.domain.Customer;
import com.qa.service.business.AccountService;
import com.qa.service.repository.CustomerRepository;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class CustomerDBRepository implements CustomerRepository{
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	@Inject
	private JSONUtil util;
	
	private static final Logger LOGGER = Logger.getLogger(CustomerDBRepository.class);
	
	@Override
	public String getCustomer(String USERNAME, String PASSWORD) {
		Query query = manager.createQuery("Select a FROM Customer a where USERNAME = '"+ USERNAME +"' and PASSWORD = '"+ PASSWORD +"'");
		Collection<Customer> customers = (Collection<Customer>) query.getResultList();
		LOGGER.info("At Customer DB repo - Get request - getCustomer");
		LOGGER.info(USERNAME + "----" + PASSWORD);
		LOGGER.info(customers);
		if (customers.size() > 1 || customers.size() == 0) {
			return "{\"result\":\"fail\"}";
		}
		else if (customers.size() == 1) {
			Customer customer = customers.iterator().next();
			Long id = customer.getId();
			LOGGER.info(id);
			return "{\"result\":\"" + id.toString() + "\"}";
		}

		return "{\"object\":\"hello\"}";
//		return util.getJSONForObject(customers);
	}

	@Override
	@Transactional(REQUIRED)
	public String createCustomer(String FIRST_NAME, String SECOND_NAME, String USERNAME, String PASSWORD) {
		Query query = manager.createQuery("insert into Customer (FIRST_NAME, SECOND_NAME, USERNAME, PASSWORD) values (" + FIRST_NAME + ", " + SECOND_NAME + ", " + USERNAME + ", " + PASSWORD + ") ");
//		manager.persist(query);
		return "";
	}

//	@Override
//	@Transactional(REQUIRED)
//	public String updateCustomer(Long id, String customerToUpdate) {
//		Customer updatedCustomer = util.getObjectForJSON(customerToUpdate, Customer.class);
//		Customer customerFromDB = findCustomer(id);
//		if (customerToUpdate != null) {
//			customerFromDB = updatedCustomer;
//			manager.merge(customerFromDB);
//		}
//		return "{\"Message\": \"Customer successfully updated\"}";
//	}
//
//	@Override
//	@Transactional(REQUIRED)
//	public String deleteCustomer(Long id) {
//		Customer customerInDB = findCustomer(id);
//		if (customerInDB != null) {
//			manager.remove(customerInDB);
//			return "{\"Message\": \"Customer successfully removed\"}";
//		}
//		return "{\"Message\": \"Customer cannot be found\"}";
//	}
	
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
