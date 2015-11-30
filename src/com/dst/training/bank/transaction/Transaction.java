package com.dst.training.bank.transaction;

import java.util.Date;

import com.dst.training.bank.Bank;
import com.dst.training.bank.account.Account;
import com.dst.training.bank.utilities.TransactionParser;

/**
*
* Class description
*
* A class describes the abstraction of transactions
* validate() and operate() method are needed to be implemented for subclasses
* Also, handle static method to produce different types of concrete-class objects for Bank
*
* @author  Woranat Kitiyanan
*/

public abstract class Transaction implements ITransaction {

	private Account fromAccount;
	private Account toAccount;
	private Date processDate;
	private double amount;
	private boolean valid;
	
	protected abstract boolean validate();
	protected abstract void operate();
	
    /**
     * Process all steps needed for a transaction
     * Invalid transaction will be not operated
     * @return true if transaction is valid
     */
	@Override
	public boolean process(){
		valid = validate();
		if(valid){
			activateAccounts();
			operate();
		}
		transactionLog();
		return valid;
	}

	/**
     * Populate values from transaction parser into itself
     * @param tp the TransactionParser
     * @return void
     */
	@Override
	public void build(TransactionParser tp){
		Bank bank = Bank.getInstance();
		
		setFromAccount(bank.getAccount(tp.getFromAccountNumber()));
		setToAccount(bank.getAccount(tp.getToAccountNumber()));
		setProcessDate(tp.getProcessDate());
		setAmount(tp.getAmount());
	}

    /**
     * Static method to instantiate transaction with different types
     * depends on method getTransactionType() of TransactionParser
     * @return Interface of transaction
     */
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
			case 'P': {
				transaction = new PurgeTransaction();
				break;
			}
			case 'T': {
				transaction = new TransferTransaction();
				break;
			}
			default :{
				System.out.printf("[ERROR]      Transaction type '%s' is not valid!%n", parser.getTransactionType());
			}
		}
		return transaction;
	}
	
    /**
     * Try to activate involved accounts (fromAccount and toAccount)
     * @return void
     */
	private void activateAccounts(){
		activateAccount(getFromAccount());
		activateAccount(getToAccount());
	}

	/**
     * Activate account if not null and status is 'C'lose
     * @return void
     */
	private void activateAccount(Account account) {
		if(account != null && account.getStatus() == 'C'){
			getFromAccount().setStatus('A');
			accountActivationLog(account);
		}
	}

	private void accountActivationLog(Account account) {
		System.out.println("[Activation] " + account.getAccountNumber() + " is activated!");
	}

	protected void transactionLog(){
		String transactionName = this.getClass().getSimpleName().replace("Transaction", "");
		String message = valid ? "[SUCCESS]":"[ERROR]";
		String toAccountNumber = getToAccount() !=  null ? "TO "+getToAccount().getAccountNumber() : "";
		System.out.printf("%-13s%-11s %11s %14s Amount: %s %n", message, transactionName, getFromAccount().getAccountNumber(), toAccountNumber, getAmount());
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
