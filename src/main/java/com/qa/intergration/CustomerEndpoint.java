package com.qa.intergration;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import com.qa.util.JSONUtil;
import com.qa.domain.Customer;
import com.qa.service.business.CustomerService;

@Path("")
public class CustomerEndpoint {
	
	@Inject
	private CustomerService service;
	
	@Inject
	private JSONUtil util;
	
	private static final Logger LOGGER = Logger.getLogger(CustomerEndpoint.class);
	
	@Path("/home")
	@PUT
	@Produces({ "application/json" })
	public String getCustomer(String JSON) {
		Customer signIn = util.getObjectForJSON(JSON, Customer.class);		
		LOGGER.info("At Customer End Point - Put request - getCustomer");
		LOGGER.info(signIn.getUserName() + "----" + signIn.getPassword());
		return service.getCustomer(signIn.getUserName(), signIn.getPassword());
	}
	
	@Path("/register")
	@PUT
	@Produces({"application/json"})
	public String checkUniqueUsername(String JSON) {
		Customer usernameCheck = util.getObjectForJSON(JSON, Customer.class);
		LOGGER.info("At customer end point - put request - unique username check");
		LOGGER.info(usernameCheck.getUserName());
		return service.checkUniqueUsername(usernameCheck.getUserName());
	}
	
	@Path("/register")
	@POST
	@Produces({ "application/json" })
	public String addCustomer(String JSON) {
		LOGGER.info(JSON);
		Customer newCustomer = util.getObjectForJSON(JSON, Customer.class);
		LOGGER.info("At Customer End Point - Post request - addCustomer");
		LOGGER.info(newCustomer.getUserName() + "----" + newCustomer.getPassword() + "----" + newCustomer.getFirstName() + "----" + newCustomer.getSecondName());
		return service.addCustomer(newCustomer.getFirstName(), newCustomer.getSecondName(), newCustomer.getUserName(), newCustomer.getPassword());
	}
	
	public void setService(CustomerService service) {
		this.service = service;
	}
	
}
