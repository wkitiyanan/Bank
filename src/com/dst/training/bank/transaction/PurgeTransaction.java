package com.dst.training.bank.transaction;

import com.dst.training.bank.Bank;

public class PurgeTransaction extends Transaction {

	@Override
	protected boolean validate() {
		boolean valid = false;
		
		if(getFromAccount() != null && getFromAccount().getBalance() == 0){
			valid = true;
		}
		
		return valid;
	}

	@Override
	protected void operate() {
		Bank.getInstance().removeAccount(getFromAccount());
	}

}
