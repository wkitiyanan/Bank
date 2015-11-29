package com.dst.training.bank.transaction;

import com.dst.training.bank.Bank;
import com.dst.training.bank.account.Account;

public class DepositTransaction extends Transaction{

	@Override
	protected boolean validate() {
		boolean valid = false;
		Bank bank = Bank.getInstance();
		Account fromAccount = bank.getAccount(getFromAccountNumber());
		if(getAmount() > 0 && fromAccount != null){
			valid = true;
		} else {
			System.out.println("ERROR: Cannot deposit to account " + getFromAccountNumber());
		}
		return valid;
	}

	@Override
	protected void operate() {
		Bank bank = Bank.getInstance();
		Account fromAccount = bank.getAccount(getFromAccountNumber());
		fromAccount.increaseBalance(getAmount());
	}

}
