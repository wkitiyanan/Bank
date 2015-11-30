package com.dst.training.bank.transaction;

import com.dst.training.bank.Bank;
import com.dst.training.bank.account.Account;
import com.dst.training.bank.utilities.TransactionParser;

public class OpenTransaction extends Transaction{

	@Override
	protected boolean validate() {
		boolean valid = false;
		String accountNumber = getFromAccount().getAccountNumber();

		//Valid if the new account does not exist in the bank system
		if(Bank.getInstance().getAccount(accountNumber) == null){
			valid = true;
		}
		
		return valid;
	}

	@Override
	protected void operate() {
		Bank.getInstance().addAccount(getFromAccount());
	}
	
	@Override
	public void build(TransactionParser tp){
		super.build(tp);
		
		//Instantiate account because the opened account does not exist yet
		Account account = new Account();
		account.setStatus('A');
		account.setAccountNumber(tp.getFromAccountNumber());
		account.setBalance(getAmount());
		
		setFromAccount(account);
	}
}
