package com.qa.intergration;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.service.business.CustomerService;

@Path("/customer")
public class CustomerEndpoint {
	
	@Inject
	private CustomerService service;
	
	@Path("/json")
	@GET
	@Produces({ "customer/json" })
	public String getCustomer(String customer) {
		return service.getAllCustomers();
	}
	
	@Path("/json")
	@POST
	@Produces({ "customer/json" })
	public String addCustomer(String customer) {
		return service.addCustomer(customer);
	}
	
	@Path("/json/{id}")
	@PUT
	@Produces({ "customer/json" })
	public String updateCustomer(@PathParam("id") Long id, String customer) {
		return service.updateCustomer(id, customer);
	}
	
	@Path("/json/{id}")
	@DELETE
	@Produces({ "customer/json" })
	public String deleteCustomer(@PathParam("id") Long id) {
		return service.deleteCustomer(id);
	}
	
	public void setService(CustomerService service) {
		this.service = service;
	}
	
}
