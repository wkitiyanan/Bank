package com.dst.training.bank.transaction;

import com.dst.training.bank.Bank;
import com.dst.training.bank.account.Account;

public class CloseTransaction extends Transaction{

	@Override
	protected boolean validate() {
		boolean valid = false;
		Bank bank = Bank.getInstance();
		Account account = bank.getAccount(getFromAccountNumber());
		if(account != null && account.getBalance() == 0){
			valid = true;
		} else {
			System.out.println("ERROR: Cannot close account " + getFromAccountNumber());
		}
		return valid;
	}

	@Override
	protected void operate() {
		Bank bank = Bank.getInstance();
		Account account = bank.getAccount(getFromAccountNumber());
		account.setStatus('C');
	}
	
}
