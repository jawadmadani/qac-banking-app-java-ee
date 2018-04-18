package com.qa.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSACTION")
public class Transaction {
	
	@Id
	@GeneratedValue
	
	@Column(name = "TRANS_ID")
	private Long transactionID;
	
	private String type;
	private double amount;
	private Date dateOfTransaction;
	
	@ManyToOne
	@JoinColumn(name = "ACC_ID", nullable = false)
	private Account account;

	public Transaction() {}
	
	public Transaction(Long transactionID, Account account, String type, double amount, Date dateOfTransaction) {
		this.transactionID = transactionID;
		this.account = account;
		this.type = type;
		this.amount = amount;
		this.dateOfTransaction = dateOfTransaction;
	}

	public Long getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(Long transactionID) {
		this.transactionID = transactionID;
	}

	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(Date dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}

}
