package com.dst.training.bank.transaction;

import java.util.Date;

import com.dst.training.bank.Bank;
import com.dst.training.bank.account.Account;
import com.dst.training.bank.utilities.TransactionParser;

/**
*
*Class description
*
* A class describes the abstraction of transactions
* validate() and operate() method are needed to be implemented for subclasses
* Also, handle static method to produce concrete-class object for Bank
*
* @author  Woranat Kitiyanan
*/

public abstract class Transaction implements ITransaction {

	private Account fromAccount;
	private Account toAccount;
	private Date processDate;
	private double amount;
	
	protected abstract boolean validate();
	protected abstract void operate();
	
	@Override
	public boolean process(){
		boolean valid = validate();
		if(valid){
			activateAccounts();
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
	
	private void activateAccounts(){
		activateAccount(getFromAccount());
		activateAccount(getToAccount());
	}

	private void activateAccount(Account account) {
		if(account != null && account.getStatus() == 'C'){
			getFromAccount().setStatus('A');
			accountActivationLog(account);
		}
	}

	private void accountActivationLog(Account account) {
		System.out.println("Account " + account.getAccountNumber() + " is activated!");
	}

	protected void transactionLog(){
		String transactionName = this.getClass().getSimpleName().replace("Transaction", "");
		System.out.printf("%-11s %11s Amount: %s %n", transactionName, getFromAccount().getAccountNumber(), getAmount());
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
