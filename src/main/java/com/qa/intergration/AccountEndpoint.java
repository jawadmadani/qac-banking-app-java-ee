package com.qa.intergration;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import com.qa.domain.Account;
import com.qa.domain.Customer;
import com.qa.service.business.AccountService;
import com.qa.util.JSONUtil;

@Path("/customer/{CUS_ID}/accounts")
public class AccountEndpoint {

	@Inject
	private AccountService service; //this links to the class AccountService in the project hierarchy

	@Inject
	private JSONUtil util;
	
	private static final Logger LOGGER = Logger.getLogger(CustomerEndpoint.class);
	
	@Path("/")
	@GET
	@Produces({ "application/json" })
	public String getAllAccounts(@PathParam("CUS_ID") Long CUS_ID) {
		return service.getAllAccounts(CUS_ID);
	}

	@Path("/new")
	@POST
	@Produces({ "application/json" })
	public String createAccount(@PathParam("CUS_ID") Long CUS_ID, String JSON) {
		LOGGER.info(JSON);
		Account newAccount = util.getObjectForJSON(JSON, Account.class);
		LOGGER.info("At Account End Point - Post request - add account");
		LOGGER.info("---" + newAccount.getAccountNumber() + "---");
		return service.createAccount(newAccount.getAccountNumber(), CUS_ID);
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
