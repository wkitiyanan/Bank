package com.dst.training.bank.transaction;

import com.dst.training.bank.Bank;
import com.dst.training.bank.account.Account;

public class DepositTransaction extends Transaction{

	@Override
	public boolean process() {
		
		boolean valid = false;
		Bank bank = Bank.getInstance();
		Account fromAccount = bank.getAccount(getFromAccountNumber());
		if(getAmount() > 0 && fromAccount != null){
			valid = true;
		}
		if(valid){
			fromAccount.increaseBalance(getAmount());
		}
		
		return valid;
	}

}
