package com.dst.training.bank.transaction;

import com.dst.training.bank.Bank;
import com.dst.training.bank.account.Account;

public class CloseTransaction extends Transaction{

	@Override
	public boolean process() {
		boolean valid = false;
		Bank bank = Bank.getInstance();
		Account account = bank.getAccount(getFromAccountNumber());
		if(account != null && account.getBalance() == 0){
			valid = true;
			account.setStatus('C');
		}
		return valid;
	}

}
