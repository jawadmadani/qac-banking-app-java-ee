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
		LOGGER.info("At Customer End Point - Get request - getCustomer");
		LOGGER.info(signIn.getUserName() + "----" + signIn.getPassword());
		return service.getCustomer(signIn.getUserName(), signIn.getPassword());
	}
	
	@Path("/register")
	@POST
	@Produces({ "application/json" })
	public String addCustomer(String FIRST_NAME, String SECOND_NAME, String USERNAME, String PASSWORD) {
		return service.addCustomer(FIRST_NAME, SECOND_NAME, USERNAME, PASSWORD);
	}
	
//	@Path("/json/{id}")
//	@PUT
//	@Produces({ "customer/json" })
//	public String updateCustomer(@PathParam("id") Long id, String customer) {
//		return service.updateCustomer(id, customer);
//	}
//	
//	@Path("/json/{id}")
//	@DELETE
//	@Produces({ "customer/json" })
//	public String deleteCustomer(@PathParam("id") Long id) {
//		return service.deleteCustomer(id);
//	}
	
	public void setService(CustomerService service) {
		this.service = service;
	}
	
}
