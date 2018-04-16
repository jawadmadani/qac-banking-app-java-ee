package com.qa.intergration;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.service.business.AccountService;

@Path("/customer/{CUS_ID}/accounts")
public class AccountEndpoint {

	@Inject
	private AccountService service; //this links to the class AccountService in the project hierarchy

	@Path("/")
	@GET
	@Produces({ "/json" })
	public String getAllAccounts(@PathParam("CUS_ID") Long CUS_ID) {
		return service.getAllAccounts(CUS_ID);
	}

	@Path("/new")
	@POST
	@Produces({ "/json" })
	public String createAccount(String ACCOUNT_NUMBER, @PathParam("CUS_ID") Long CUS_ID) {
		return service.createAccount(ACCOUNT_NUMBER, CUS_ID);
	}
//
//	@Path("/json/{id}")
//	@PUT
//	@Produces({ "application/json" })
//	public String updateAccount(@PathParam("id") Long id, String account) {
//		return service.updateAccount(id, account);
//	}
//
//	@Path("/json/{id}")
//	@DELETE
//	@Produces({ "application/json" })
//	public String deleteAccount(@PathParam("id") Long id) {
//		return service.deleteAccount(id);
//	}

	public void setService(AccountService service) {
		this.service = service;
	}

}
