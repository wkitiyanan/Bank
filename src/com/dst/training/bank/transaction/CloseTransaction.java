package com.dst.training.bank.transaction;

import com.dst.training.bank.account.Account;

public class CloseTransaction extends Transaction{

	@Override
	protected boolean validate() {
		boolean valid = false;
		Account account = getFromAccount();
		
		//Invalid if account does not exist or balance is not zero
		if(account != null && account.getBalance() == 0){
			valid = true;
		}
		return valid;
	}

	@Override
	protected void operate() {
		getFromAccount().setStatus('C');
	}
	
}
