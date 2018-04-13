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

import com.qa.domain.Transaction;
import com.qa.service.repository.TransactionRepository;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class TransactionDBRepository implements TransactionRepository{
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	@Inject
	private JSONUtil util;

	@Override
	public String getAllTransactions(long accountid) {
		Query query = manager.createQuery("Select a FROM Transaction a where ACC_ID = " + accountid + ";");
		Collection<Transaction> transactions = (Collection<Transaction>) query.getResultList();
		return util.getJSONForObject(transactions);
	}
	
	@Override
	public String getAllTransactionStatement(long accountid) {
		Query query = manager.createQuery("Select a FROM Transaction a where ACC_ID = " + accountid + ";");
		Collection<Transaction> transactions = (Collection<Transaction>) query.getResultList();
		return util.getJSONForObject(transactions);
	}

	@Override
	@Transactional(REQUIRED)
	public String createTransaction(String transaction) {
		Transaction aTransaction = util.getObjectForJSON(transaction, Transaction.class);
		manager.persist(aTransaction);
		return "{\"message\": \"Transaction was successful\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String updateTransaction(Long id, String transactionToUpdate) {
		Transaction updatedTransaction = util.getObjectForJSON(transactionToUpdate, Transaction.class);
		Transaction transactionFromDB = findTransaction(id);
		if(transactionToUpdate != null) {
			transactionFromDB = updatedTransaction;
			manager.merge(transactionFromDB);
		}
		return "{\"Message\": \"Transaction successfully updated\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteTransaction(Long id) {
		Transaction transactionInDB = findTransaction(id);
		if(transactionInDB != null) {
			manager.remove(transactionInDB);
			return "{\"Message\": \"Transaction successfully removed\"}";
		}
		return "{\"Message\": \"Transaction cannot be found\"}";
	}
	
	private Transaction findTransaction(Long id) {
		return manager.find(Transaction.class, id);
	}
	
	private void setManager(EntityManager manager) {
		this.manager = manager;
	}
	
	public void setUtil(JSONUtil util) {
		this.util = util;
	}

	

}
