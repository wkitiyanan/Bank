package com.dst.training.bank.transaction;

import com.dst.training.bank.Bank;
import com.dst.training.bank.account.Account;

public class WithdrawalTransaction extends Transaction{

	@Override
	protected boolean validate() {
		boolean valid = false;
		Bank bank = Bank.getInstance();
		Account fromAccount = bank.getAccount(getFromAccountNumber());
		if(getAmount() > 0
				&& fromAccount != null
				&& fromAccount.getBalance() >= getAmount()){
			valid = true;
		} else {
			System.out.println("ERROR: Cannot withdraw from " + getFromAccountNumber());
		}
		
		return valid;
	}

	@Override
	protected void operate() {
		Bank bank = Bank.getInstance();
		Account fromAccount = bank.getAccount(getFromAccountNumber());
		fromAccount.decreaseBalance(getAmount());
		if(fromAccount.getBalance() == 0) fromAccount.setStatus('C');
	}
}
