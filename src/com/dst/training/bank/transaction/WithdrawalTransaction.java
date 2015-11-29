package com.dst.training.bank.transaction;

import com.dst.training.bank.account.Account;

public class WithdrawalTransaction extends Transaction{

	@Override
	protected boolean validate() {
		boolean valid = false;
		Account account = getFromAccount();
		if(account != null
				&& getAmount() > 0
				&& account.getBalance() >= getAmount()){
			valid = true;
		} else {
			System.out.println("ERROR: Cannot withdraw from " + getFromAccount());
		}
		
		return valid;
	}

	@Override
	protected void operate() {
		Account account = getFromAccount();
		account.decreaseBalance(getAmount());
		if(account.getBalance() == 0) account.setStatus('C');
	}
}
