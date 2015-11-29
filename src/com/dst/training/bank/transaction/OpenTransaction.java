package com.dst.training.bank.transaction;

import com.dst.training.bank.Bank;
import com.dst.training.bank.account.Account;
import com.dst.training.bank.utilities.TransactionParser;

public class OpenTransaction extends Transaction{

	@Override
	protected boolean validate() {
		return true;
	}

	@Override
	protected void operate() {
		Bank.getInstance().addAccount(getFromAccount());
	}
	
	@Override
	public void build(TransactionParser tp){
		super.build(tp);
		
		Account account = new Account();
		account.setStatus('A');
		account.setAccountNumber(tp.getFromAccountNumber());
		account.setBalance(getAmount());
		
		setFromAccount(account);
	}
}
