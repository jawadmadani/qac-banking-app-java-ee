package com.qa.intergration;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.service.business.TransactionService;

@Path("/transaction")
public class TransactionEndpoint {
	
	@Inject
	private TransactionService service;
	
	@Path("/json")
	@GET
	@Produces({ "transaction/json" })
	public String getTransaction(String transaction) {
		return service.getAllTransactions();
	}
	
	@Path("/json")
	@POST
	@Produces({ "transaction/json" })
	public String addTransaction(String transaction) {
		return service.addTransaction(transaction);
	}
	
	@Path("/json/{id}")
	@PUT
	@Produces({ "transaction/json" })
	public String updateTransaction(@PathParam("id") Long id, String transaction) {
		return service.updateTransaction(id, transaction);
	}
	
	@Path("/json/{id}")
	@DELETE
	@Produces({ "transaction/json" })
	public String deleteTransaction(@PathParam("id") Long id) {
		return service.deleteTransaction(id);
	}
	
	public void setService(TransactionService service) {
		this.service = service;
	}
	
}
