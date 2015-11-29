package com.dst.training.bank.transaction;

import com.dst.training.bank.Bank;
import com.dst.training.bank.account.Account;

public class WithdrawalTransaction extends Transaction{

	@Override
	public boolean process() {
		boolean valid = false;
		Bank bank = Bank.getInstance();
		Account fromAccount = bank.getAccount(getFromAccountNumber());
		if(getAmount() > 0
				&& fromAccount != null
				&& fromAccount.getBalance() >= getAmount()){
			
			valid = true;
		}
		if(valid){
			fromAccount.decreaseBalance(getAmount());
			if(fromAccount.getBalance() == 0) fromAccount.setStatus('C');
		}
		
		return valid;
	}
}
