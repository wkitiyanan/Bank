package com.dst.training.bank.transaction;

import com.dst.training.bank.Bank;
import com.dst.training.bank.account.Account;

public class OpenTransaction extends Transaction{

	@Override
	public boolean process() {
		boolean valid = true;
		try {
			Account account = new Account();
			account.setStatus('A');
			account.setAccountNumber(getFromAccountNumber());
			account.setBalance(getAmount());
			Bank.getInstance().addAccount(account);
		} catch (NullPointerException e) {
			// If instance of bank is null, the transaction will be invalid
			valid = false;
		}
		return valid;
	}

}
