package com.dst.training.bank.transaction;

import com.dst.training.bank.Bank;
import com.dst.training.bank.account.Account;

public class OpenTransaction extends Transaction{

	@Override
	protected boolean validate() {
		return true;
	}

	@Override
	protected void operate() {
		Account account = new Account();
		account.setStatus('A');
		account.setAccountNumber(getFromAccountNumber());
		account.setBalance(getAmount());
		Bank.getInstance().addAccount(account);
	}

}
