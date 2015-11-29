package com.dst.training.bank.transaction;

import com.dst.training.bank.account.Account;

public class CloseTransaction extends Transaction{

	@Override
	protected boolean validate() {
		boolean valid = false;
		Account account = getFromAccount();
		if(account != null && account.getBalance() == 0){
			valid = true;
		} else {
			System.out.println("ERROR: Cannot close account " + account);
		}
		return valid;
	}

	@Override
	protected void operate() {
		getFromAccount().setStatus('C');
	}
	
}
