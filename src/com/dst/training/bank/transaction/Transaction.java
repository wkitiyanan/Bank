package com.dst.training.bank.transaction;

import java.util.Date;

import com.dst.training.bank.utilities.TransactionParser;

public abstract class Transaction implements ITransaction {

	private String fromAccountNumber;
	private String toAccountNumber;
	private Date processDate;
	private double amount;
	
	@Override
	public boolean process(){
		boolean valid = validate();
		if(valid){
			operate();
			transactionLog();
		}
		return valid;
	}

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
	
	protected abstract boolean validate();
	protected abstract void operate();
	protected void transactionLog(){
		String transactionName = this.getClass().getSimpleName().replace("Transaction", "");
		System.out.printf("%-11s%11s Amount: %s\n", transactionName, getFromAccountNumber(), getAmount());
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
