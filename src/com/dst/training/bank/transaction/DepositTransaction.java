package com.dst.training.bank.transaction;

import com.dst.training.bank.account.Account;

public class DepositTransaction extends Transaction{

	@Override
	protected boolean validate() {
		boolean valid = false;
		Account account = getFromAccount();
		
		//Amount must be greater than zero and account must be existing to be valid
		if(account != null && getAmount() > 0){
			valid = true;
		}
		
		return valid;
	}

	@Override
	protected void operate() {
		Account fromAccount = getFromAccount();
		fromAccount.increaseBalance(getAmount());
	}

}
