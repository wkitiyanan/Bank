package com.dst.training.bank.transaction;

import java.util.Date;

import com.dst.training.bank.Bank;
import com.dst.training.bank.account.Account;
import com.dst.training.bank.utilities.TransactionParser;

public abstract class Transaction implements ITransaction {

	private Account fromAccount;
	private Account toAccount;
	private Date processDate;
	private double amount;
	
	@Override
	public boolean process(){
		boolean valid = validate();
		if(valid){
			activate();
			operate();
			transactionLog();
		}
		return valid;
	}

	@Override
	public void build(TransactionParser tp){
		Bank bank = Bank.getInstance();
		setFromAccount(bank.getAccount(tp.getFromAccountNumber()));
		setToAccount(bank.getAccount(tp.getToAccountNumber()));
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
	
	private void activate(){
		if(getFromAccount() != null && getFromAccount().getStatus() == 'C'){
			getFromAccount().setStatus('A');
			System.out.println(getFromAccount().getAccountNumber() + " is activated");
		}
		if(getToAccount() != null && getToAccount().getStatus() == 'C'){
			getToAccount().setStatus('A');
			System.out.println(getToAccount().getAccountNumber() + " is activated");
		}
	}
	
	protected void transactionLog(){
		String transactionName = this.getClass().getSimpleName().replace("Transaction", "");
		System.out.printf("%-11s%11s Amount: %s\n", transactionName, getFromAccount(), getAmount());
	}

	public Account getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}

	public Account getToAccount() {
		return toAccount;
	}

	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
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
