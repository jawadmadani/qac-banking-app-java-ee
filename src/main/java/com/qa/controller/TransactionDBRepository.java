package com.qa.controller;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;

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
	
	private static final Logger LOGGER = Logger.getLogger(TransactionDBRepository.class);
	
	@Override
	public String getBalance(long ACC_ID) {
		LOGGER.info("At Transaction DB repo - Get request - getBalance");
		Query queryBalance = manager.createQuery("Select ((Select amount FROM Transaction a where TYPE = 'DEPOSIT' and ACC_ID = " + ACC_ID + ") + "
				+ "((Select amount FROM Transaction a where TYPE = 'WITHDRAWAL' and ACC_ID = " + ACC_ID + ")*-1)) "
				+ "FROM Transaction a where TRANS_ID = 1");
		Collection<Transaction> balance = (Collection<Transaction>) queryBalance.getResultList();
		
		if(balance.size() != 0) {
			LOGGER.info(balance.size());
			return "{\"Balance\": \"" + balance + "\"}";
		}else { return "{\"message\": \"Balance statement was unsuccessful\"}";}
		
	}

	@Override
	public String getAllTransactionStatement(long ACC_ID) {
		LOGGER.info("At Transaction DB repo - Get request - getAllTransactionStatement");
		Query query = manager.createQuery("Select a FROM Transaction a where ACC_ID = " + ACC_ID);
		Collection<Transaction> transactions = (Collection<Transaction>) query.getResultList();
		
		String stringJson = "";
		if(transactions.size() != 0) {
			for(Transaction transaction : transactions) {
				stringJson = stringJson + "{\"TRANS_ID\": \"" + transaction.getTransactionID() + 
						"\", \"ACC_ID\": \"" + transaction.getAccount().getId() + 
						"\", \"TYPE\": \"" + transaction.getType() + 
						"\", \"DATEOFTRANSACTION\": \"" + transaction.getDateOfTransaction() + 
						"\", \"AMOUNT\": \"" + transaction.getAmount() + "\"}, ";
			}
		}else { return "{\"message\": \"Transaction statement was unsuccessful\"}"; }
		
		stringJson = stringJson.substring(0, (stringJson.length() - 2));
		
		String stringJsonReturn = "[" + stringJson + "]";
		return stringJsonReturn;
			
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
