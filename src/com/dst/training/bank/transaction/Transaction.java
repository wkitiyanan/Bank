package com.dst.training.bank.transaction;

import java.util.Date;

import com.dst.training.bank.utilities.TransactionParser;

public abstract class Transaction implements ITransaction {

	private String fromAccountNumber;
	private String toAccountNumber;
	private Date processDate;
	private double amount;
	
	@Override
	public abstract boolean process();

	@Override
	public void build(TransactionParser tp){
		setFromAccountNumber(tp.getFromAccountNumber());
		setToAccountNumber(tp.getToAccountNumber());
		setProcessDate(tp.getProcessDate());
		setAmount(tp.getAmount());
	}

	public static ITransaction getTransaction(TransactionParser parser) {
		ITransaction transaction = null;
		switch(parser.getTransactionType()){
			case 'D': {
				transaction = new DepositTransaction();
				break;
			}
			case 'W': {
				transaction = new WithdrawalTransaction();
				break;
			}
			case 'O': {
				transaction = new OpenTransaction();
				break;
			}
			case 'C': {
				transaction = new CloseTransaction();
				break;
			}
		}
		return transaction;
	}

	public String getFromAccountNumber() {
		return fromAccountNumber;
	}

	public void setFromAccountNumber(String fromAccountNumber) {
		this.fromAccountNumber = fromAccountNumber;
	}

	public String getToAccountNumber() {
		return toAccountNumber;
	}

	public void setToAccountNumber(String toAccountNumber) {
		this.toAccountNumber = toAccountNumber;
	}

	public Date getProcessDate() {
		return processDate;
	}

	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
