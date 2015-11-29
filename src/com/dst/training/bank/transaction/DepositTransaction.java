package com.dst.training.bank.transaction;

import com.dst.training.bank.account.Account;

public class DepositTransaction extends Transaction{

	@Override
	protected boolean validate() {
		boolean valid = false;
		Account account = getFromAccount();
		if(account != null && getAmount() > 0){
			valid = true;
		} else {
			System.out.println("ERROR: Cannot deposit to account " + getFromAccount());
		}
		return valid;
	}

	@Override
	protected void operate() {
		Account fromAccount = getFromAccount();
		fromAccount.increaseBalance(getAmount());
	}

}
