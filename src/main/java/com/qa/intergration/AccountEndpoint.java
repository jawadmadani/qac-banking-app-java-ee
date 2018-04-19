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
	private AccountService service;
  
	private static final Logger LOGGER = Logger.getLogger(AccountEndpoint.class);

	@Inject
	private JSONUtil util;
  
	@Path("/")
	@GET
	@Produces({ "application/json" })
	public String getAllAccounts(@PathParam("CUS_ID") Long CUS_ID) {
		LOGGER.info("At AccountEndPoint - GET Request - Get All Accounts");
		LOGGER.info("Customer id: " + CUS_ID);
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

	@Path("/{ACC_ID}")
	@DELETE
	@Produces({ "application/json" })
	public String deleteAccount(@PathParam("ACC_ID") Long ACC_ID) {
		LOGGER.info("At AccountEndPoint - Delete request - deleteAccount");
		LOGGER.info("account id: " + ACC_ID);
		return service.deleteAccount(ACC_ID);
	}

	public void setService(AccountService service) {
		this.service = service;
	}

}
